package br.com.fiap.autorizadora.service

import br.com.fiap.autorizadora.exceptions.NaoPossuiSaldoException
import br.com.fiap.autorizadora.exceptions.ValorGastoOuValorPagamentoPossuemValorNegativoException
import br.com.fiap.autorizadora.model.*
import br.com.fiap.autorizadora.requester.AlunoRequester
import br.com.fiap.autorizadora.requester.TransacaoRequester
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AutorizadoraService( val alunoRequester: AlunoRequester, val transacaoRequester: TransacaoRequester) {

    fun comprar(compra: Compra){
        val aluno = buscarAluno(compra.identificadorAluno)

        aluno.gasto = aluno.gasto + compra.valor

        if (aluno.gasto < aluno.limite) {
            val transacao = criarTransacao(compra.identificadorAluno, compra.valor, aluno.gasto, TipoTransacao.COMPRA, compra.descricao)
            atualizarSaldo(compra.identificadorAluno, transacao)
        } else {
            throw NaoPossuiSaldoException("Saldo Insuficiente!")
        }
    }

    fun pagar(pagamento: Pagamento) {
        val aluno = buscarAluno(pagamento.identificadorAluno)

        aluno.gasto = aluno.gasto - pagamento.valor

        if (aluno.gasto > BigDecimal.ZERO && pagamento.valor > BigDecimal.ZERO) {
            val transacao = criarTransacao(pagamento.identificadorAluno, pagamento.valor, aluno.gasto, TipoTransacao.PAGAMENTO, pagamento.descricao)
            atualizarSaldo(pagamento.identificadorAluno, transacao)
        } else {
            throw ValorGastoOuValorPagamentoPossuemValorNegativoException("O saldo final ou valor pagamento é negativo!")
        }
    }

    fun buscarAluno(identificadorAluno: String): Aluno = alunoRequester.getAluno(identificadorAluno)

    fun atualizarSaldo(identificadorAluno: String, transacao: Transacao) {
        try {
            transacaoRequester.atualizarSaldo(identificadorAluno, transacao)
        } catch (ex : Exception) {
            throw ex
        }
    }

    fun criarTransacao(
        identificadorAluno: String,
        valorTransacao: BigDecimal,
        saldoAluno: BigDecimal,
        tipoTransacao: TipoTransacao,
        descricao: String
    ): Transacao =
        Transacao(
            identificador = identificadorAluno,
            valorTransacao = valorTransacao,
            valorSaldoAluno = saldoAluno,
            tipoTransacao = tipoTransacao,
            descricao = descricao
        )

}