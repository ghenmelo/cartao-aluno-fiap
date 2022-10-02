package br.com.fiap.autorizadora.requester

import br.com.fiap.autorizadora.model.Aluno
import br.com.fiap.autorizadora.model.TipoTransacao
import br.com.fiap.autorizadora.model.Transacao
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "alunos" ,url = "\${aluno.url}")
interface AlunoRequester {

    @GetMapping("{identificadorAluno}")
    fun getAluno(@PathVariable identificadorAluno: String): Aluno
}