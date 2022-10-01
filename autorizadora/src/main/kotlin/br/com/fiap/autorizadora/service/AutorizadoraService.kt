package br.com.fiap.autorizadora.service

import br.com.fiap.autorizadora.exceptions.NaoPossuiSaldoException
import br.com.fiap.autorizadora.exceptions.ValorGastoOuValorPagamentoPossuemValorNegativoException
import br.com.fiap.autorizadora.model.Aluno
import br.com.fiap.autorizadora.model.Compra
import br.com.fiap.autorizadora.model.Pagamento
import br.com.fiap.autorizadora.requester.AlunoRequester
import java.math.BigDecimal

class AutorizadoraService( val alunoRequester: AlunoRequester) {

    fun comprar(compra: Compra) {
        val aluno = buscarAluno(compra.identificadorAluno)

        aluno.gasto = aluno.gasto + compra.valor

        if (aluno.gasto < aluno.limite) {
            alunoRequester.atualizarSaldo(compra.identificadorAluno, aluno);
        } else {
            throw NaoPossuiSaldoException("Saldo Insuficiente!")
        }
    }

    fun pagar(pagamento: Pagamento) {
        val aluno = buscarAluno(pagamento.identificadorAluno)

        aluno.gasto = aluno.gasto - pagamento.valor

        if (aluno.gasto > BigDecimal.ZERO && pagamento.valor > BigDecimal.ZERO) {
            alunoRequester.atualizarSaldo(pagamento.identificadorAluno, aluno);
        } else {
            throw ValorGastoOuValorPagamentoPossuemValorNegativoException("O saldo final ou valor pagamento é negativo!")
        }
    }

    fun buscarAluno(identificadorAluno: String): Aluno = alunoRequester.getAluno(identificadorAluno);
}