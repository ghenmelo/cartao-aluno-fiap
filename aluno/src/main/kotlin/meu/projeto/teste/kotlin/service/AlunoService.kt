package meu.projeto.teste.kotlin.service

import meu.projeto.teste.kotlin.exception.NaoEncontrouAlunoException
import meu.projeto.teste.kotlin.models.Aluno
import meu.projeto.teste.kotlin.repository.AlunoRepository
import org.springframework.stereotype.Service

@Service
class AlunoService (val alunoRepository: AlunoRepository) {

    fun buscar() = alunoRepository.findAll()

    fun criar(aluno: Aluno) = alunoRepository.save(aluno)

    fun buscarAluno(id: String) =
        alunoRepository.findById(id)
            .orElseThrow { NaoEncontrouAlunoException("Nao encontrou Aluno") }

    fun atualizar(id: String, aluno: Aluno): Aluno {
        return alunoRepository.save(
            buscarAluno(id)
                .copy(
                    nome = aluno.nome,
                    identificador = aluno.identificador,
                    codigoMatricula = aluno.codigoMatricula
                )
        )
    }

    fun deletar(document: String) = alunoRepository.delete(buscarAluno(document))
}