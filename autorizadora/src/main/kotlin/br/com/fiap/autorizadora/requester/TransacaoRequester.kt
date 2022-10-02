package br.com.fiap.autorizadora.requester

import br.com.fiap.autorizadora.model.Transacao
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping

@FeignClient(name = "transacao" ,url = "\${transacao.url}")
interface TransacaoRequester {

    @PutMapping("{identificadorAluno}")
    fun atualizarSaldo(@PathVariable identificadorAluno: String, transacao: Transacao)
}