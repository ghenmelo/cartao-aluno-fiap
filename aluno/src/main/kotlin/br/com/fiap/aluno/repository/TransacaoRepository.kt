package br.com.fiap.aluno.repository

import br.com.fiap.aluno.models.Transacao
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TransacaoRepository: MongoRepository<Transacao, String> {

    fun findAllByIdentificadorOrderByDataTransacaoDesc(identificador: String): List<Transacao>
}