package br.com.fiap.aluno

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication(exclude = [ DataSourceAutoConfiguration::class ] )
@EnableConfigurationProperties
@EnableBatchProcessing
class KotlinApplication

fun main(args: Array<String>) {
	runApplication<KotlinApplication>(*args)
}

