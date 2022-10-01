package meu.projeto.teste.kotlin.config
import meu.projeto.teste.kotlin.models.Aluno
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.data.MongoItemWriter
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.LineMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.FixedLengthTokenizer
import org.springframework.batch.item.file.transform.Range
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.PathResource
import org.springframework.data.mongodb.core.MongoTemplate
import java.math.BigDecimal

@Configuration
class BatchConfiguration(
    val filePath: FilePath
) {

    @Bean
    fun itemReader(): ItemReader<Aluno>? {
        val flatFileItemReader: FlatFileItemReader<Aluno> = FlatFileItemReader<Aluno>()
        flatFileItemReader.setResource(PathResource(filePath.inputfile))
        flatFileItemReader.setName("File-Reader")
        flatFileItemReader.setLineMapper(LineMapper())
        return flatFileItemReader
    }

    @Bean
    fun LineMapper(): LineMapper<Aluno> {
        val defaultLineMapper: DefaultLineMapper<Aluno> = DefaultLineMapper<Aluno>()
        val fixedLengthTokenizer = FixedLengthTokenizer()
        fixedLengthTokenizer.setNames("nome", "identificador", "codigoMatricula")
        fixedLengthTokenizer.setColumns(Range(1,41), Range(43,49), Range(50,56))
        fixedLengthTokenizer.setStrict(false)

        defaultLineMapper.setLineTokenizer(fixedLengthTokenizer)
        defaultLineMapper.setFieldSetMapper {
            Aluno(
                nome = it.readString("nome"),
                identificador = it.readString("identificador"),
                codigoMatricula = it.readString("codigoMatricula"),
                gasto = BigDecimal.ZERO,
                limite = BigDecimal.ZERO
            )
        }
        return defaultLineMapper
    }

    @Bean
    fun itemProcessor(): ItemProcessor<Aluno, Aluno?>? {
        return ItemProcessor<Aluno, Aluno?> { aluno: Aluno ->
            if (aluno.nome?.contains("-") == true || aluno.nome?.isEmpty() == true) {
                return@ItemProcessor null
            }

            return@ItemProcessor aluno.copy(
                nome = aluno.nome,
                identificador = aluno.identificador,
                codigoMatricula = aluno.codigoMatricula,
                gasto = BigDecimal.ZERO,
                limite = BigDecimal.ZERO
            )
        }
    }

    @Bean
    fun itemWriter(mongoTemplate: MongoTemplate): MongoItemWriter<Aluno> {
        return MongoItemWriterBuilder<Aluno>().template(mongoTemplate).collection("Aluno")
            .build()
    }

    @Bean
    fun step(
        stepBuilderFactory: StepBuilderFactory,
        itemProcessor: ItemProcessor<Aluno?, Aluno?>,
        itemWriter: MongoItemWriter<Aluno?>,
        itemReader: FlatFileItemReader<Aluno>
    ): Step {
        return stepBuilderFactory["txt aluno to database"]
            .chunk<Aluno, Aluno>(100)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .faultTolerant()
            .allowStartIfComplete(true)
            .build()
    }

    @Bean
    fun job(
        jobBuilderFactory: JobBuilderFactory,
        step: Step
    ): Job {
        return jobBuilderFactory["txt todatabase job"]
            .start(step)
            .build()
    }
}