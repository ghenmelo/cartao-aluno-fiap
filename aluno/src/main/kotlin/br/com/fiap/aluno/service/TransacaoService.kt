package br.com.fiap.aluno.service

import br.com.fiap.aluno.models.Transacao
import br.com.fiap.aluno.repository.TransacaoRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.springframework.stereotype.Service
import java.io.IOException
import java.io.Writer;

@Service
class TransacaoService (val transacaoRepository: TransacaoRepository){

    fun buscarTransacoes(identificadorAluno: String): List<Transacao> = transacaoRepository.findAllByIdentificadorOrderByDataTransacaoDesc(identificadorAluno)

    fun downloadToCsv(identificadorAluno: String, writer : Writer) {
        val transacaoList: List<Transacao> = buscarTransacoes(identificadorAluno)

        try {
            CSVPrinter(
                writer,
                CSVFormat.Builder.create().setDelimiter(',').setHeader("IDENTIFICADOR", "DESCRICAO", "TIPO", "VALOR").build()
            ).use { csvPrinter ->
                for (transacao in transacaoList) {
                    csvPrinter.printRecord(
                        transacao.identificador,
                        transacao.descricao,
                        transacao.tipoTransacao,
                        transacao.valorTransacao
                    )
                }
            }
        } catch (ex: IOException) {
            throw ex
        }
    }
}