package br.com.fiap.aluno.repository

import br.com.fiap.aluno.models.Aluno
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AlunoRepository: MongoRepository<Aluno, String> {

    fun findByidentificador(identificador: String): Optional<Aluno>
}