package meu.projeto.teste.kotlin.repository

import meu.projeto.teste.kotlin.models.Aluno
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AlunoRepository: MongoRepository<Aluno, String> {

    override fun findById(id: String): Optional<Aluno>
}