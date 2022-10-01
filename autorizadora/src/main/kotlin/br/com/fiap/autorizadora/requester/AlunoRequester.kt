package br.com.fiap.autorizadora.requester

import br.com.fiap.autorizadora.model.Aluno
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(url = "\${aluno.url}")
interface AlunoRequester {

    @GetMapping("/{identificadorAluno}")
    fun getAluno(@PathVariable identificadorAluno: String): Aluno

    @PatchMapping("/{identificadorAluno}")
    fun atualizarSaldo(@PathVariable identificadorAluno: String, @RequestBody aluno: Aluno)
}