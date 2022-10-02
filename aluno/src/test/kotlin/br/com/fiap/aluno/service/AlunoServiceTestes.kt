package br.com.fiap.aluno.service

import br.com.fiap.aluno.exception.NaoEncontrouAlunoException
import br.com.fiap.aluno.models.Aluno
import br.com.fiap.aluno.models.TipoTransacao
import br.com.fiap.aluno.models.Transacao
import br.com.fiap.aluno.repository.AlunoRepository
import br.com.fiap.aluno.repository.TransacaoRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class AlunoServiceTestes {

    @Test(expected = NaoEncontrouAlunoException::class)
    fun validaBuscaDeAluno() {
        val alunoRepository = Mockito.mock(AlunoRepository::class.java)
        val transacaoRepository = Mockito.mock(TransacaoRepository::class.java)
        val alunoService = AlunoService(alunoRepository, transacaoRepository)

        Mockito.`when`(alunoRepository.findByidentificador(Mockito.anyString())).thenReturn(Optional.ofNullable(null))

        alunoService.buscarAluno("123")
    }

    @Test
    fun validaAtualizarSaldo() {
        val alunoRepository = Mockito.mock(AlunoRepository::class.java)
        val transacaoRepository = Mockito.mock(TransacaoRepository::class.java)
        val alunoService = AlunoService(alunoRepository, transacaoRepository)

        val transacao = Transacao(
            identificador = "123",
            valorSaldoAluno = BigDecimal(100),
            valorTransacao = BigDecimal(100),
            descricao = "teste",
            tipoTransacao = TipoTransacao.COMPRA,
            dataTransacao = LocalDateTime.now()
        )

        val aluno = Aluno(
            nome = "Guilherme",
            identificador = "123",
            gasto = BigDecimal(100),
            limite = BigDecimal(1000),
            codigoMatricula = "1234"
        )

        Mockito.`when`(alunoRepository.findByidentificador(Mockito.anyString())).thenReturn(Optional.ofNullable(aluno))

        alunoService.atualizarSaldo("213", transacao)

        Assertions.assertEquals(aluno.gasto, transacao.valorSaldoAluno)
        Mockito.verify(alunoRepository).save(aluno)
        Mockito.verify(transacaoRepository).save(transacao)
    }
}