package br.com.fiap.aluno.service

import br.com.fiap.aluno.exception.NaoEncontrouAlunoException
import br.com.fiap.aluno.models.Aluno
import br.com.fiap.aluno.models.TipoTransacao
import br.com.fiap.aluno.models.Transacao
import br.com.fiap.aluno.repository.AlunoRepository
import br.com.fiap.aluno.repository.TransacaoRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AlunoService (val alunoRepository: AlunoRepository, val transacaoRepository: TransacaoRepository) {

    fun buscar() = alunoRepository.findAll()

    fun criar(aluno: Aluno) = alunoRepository.save(aluno)

    fun buscarAluno(identificador: String) =
        alunoRepository.findByidentificador(identificador)
            .orElseThrow { NaoEncontrouAlunoException("""Nao encontrou Aluno com identificador $identificador""") }

    fun atualizar(identificador: String, aluno: Aluno): Aluno {
        return alunoRepository.save(
            buscarAluno(identificador)
                .copy(
                    nome = aluno.nome,
                    limite = aluno.limite,
                    gasto = aluno.gasto
                )
        )
    }

    fun deletar(document: String) = alunoRepository.delete(buscarAluno(document))

    fun atualizarSaldo(identificadorAluno: String, transacao: Transacao) {
        val aluno = buscarAluno(identificadorAluno)

        aluno.gasto = transacao.valorSaldoAluno

        transacaoRepository.save(transacao)
        alunoRepository.save(aluno)
    }
}