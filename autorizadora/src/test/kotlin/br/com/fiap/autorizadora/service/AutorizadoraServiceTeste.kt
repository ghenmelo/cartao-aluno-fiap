package br.com.fiap.autorizadora.service

import br.com.fiap.autorizadora.exceptions.NaoPossuiSaldoException
import br.com.fiap.autorizadora.exceptions.ValorGastoOuValorPagamentoPossuemValorNegativoException
import br.com.fiap.autorizadora.model.*
import br.com.fiap.autorizadora.requester.AlunoRequester
import br.com.fiap.autorizadora.requester.TransacaoRequester
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import java.math.BigDecimal

class AutorizadoraServiceTeste {

    @Test
    fun validaComprar() {
        val alunoRequester = Mockito.mock(AlunoRequester::class.java)
        val transacaoRequester = Mockito.mock(TransacaoRequester::class.java)
        val autorizadoraService = AutorizadoraService(alunoRequester, transacaoRequester)

        val aluno = Aluno(
            nome = "Guilherme",
            identificador = "123",
            gasto = BigDecimal(100),
            limite = BigDecimal(1000),
            codigoMatricula = "1234"
        )

        val compra = Compra(
            identificadorAluno = "123",
            valor = BigDecimal.TEN,
            descricao = "Teste"
        )

        Mockito.`when`(alunoRequester.getAluno(Mockito.anyString())).thenReturn(aluno)

        autorizadoraService.comprar(compra)

        Assertions.assertEquals(aluno.gasto, BigDecimal(110))
    }

    @Test(expected = NaoPossuiSaldoException::class)
    fun validaComprarErro() {
        val alunoRequester = Mockito.mock(AlunoRequester::class.java)
        val transacaoRequester = Mockito.mock(TransacaoRequester::class.java)
        val autorizadoraService = AutorizadoraService(alunoRequester, transacaoRequester)

        val aluno = Aluno(
            nome = "Guilherme",
            identificador = "123",
            gasto = BigDecimal(100),
            limite = BigDecimal(1000),
            codigoMatricula = "1234"
        )

        val compra = Compra(
            identificadorAluno = "123",
            valor = BigDecimal(1000),
            descricao = "Teste"
        )

        Mockito.`when`(alunoRequester.getAluno(Mockito.anyString())).thenReturn(aluno)

        autorizadoraService.comprar(compra)
    }

    @Test()
    fun validaPagar() {
        val alunoRequester = Mockito.mock(AlunoRequester::class.java)
        val transacaoRequester = Mockito.mock(TransacaoRequester::class.java)
        val autorizadoraService = AutorizadoraService(alunoRequester, transacaoRequester)

        val aluno = Aluno(
            nome = "Guilherme",
            identificador = "123",
            gasto = BigDecimal(100),
            limite = BigDecimal(1000),
            codigoMatricula = "1234"
        )

        val pagamento = Pagamento(
            identificadorAluno = "123",
            valor = BigDecimal.TEN,
            descricao = "Teste1"
        )

        Mockito.`when`(alunoRequester.getAluno(Mockito.anyString())).thenReturn(aluno)

        autorizadoraService.pagar(pagamento)

        Assertions.assertEquals(aluno.gasto, BigDecimal(90))
    }

    @Test(expected = ValorGastoOuValorPagamentoPossuemValorNegativoException::class)
    fun validaPagamentoErro() {
        val alunoRequester = Mockito.mock(AlunoRequester::class.java)
        val transacaoRequester = Mockito.mock(TransacaoRequester::class.java)
        val autorizadoraService = AutorizadoraService(alunoRequester, transacaoRequester)

        val aluno = Aluno(
            nome = "Guilherme",
            identificador = "123",
            gasto = BigDecimal(100),
            limite = BigDecimal(1000),
            codigoMatricula = "1234"
        )

        val pagamento = Pagamento(
            identificadorAluno = "123",
            valor = BigDecimal(200),
            descricao = "Teste1"
        )

        Mockito.`when`(alunoRequester.getAluno(Mockito.anyString())).thenReturn(aluno)

        autorizadoraService.pagar(pagamento)
    }
}