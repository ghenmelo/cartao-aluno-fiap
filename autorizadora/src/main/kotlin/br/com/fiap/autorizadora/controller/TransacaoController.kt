package br.com.fiap.autorizadora.controller

import br.com.fiap.autorizadora.model.Compra
import br.com.fiap.autorizadora.model.Pagamento
import br.com.fiap.autorizadora.service.AutorizadoraService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@ResponseStatus(HttpStatus.ACCEPTED)
@RequestMapping("autorizadora")
class TransacaoController (val autorizadoraService: AutorizadoraService){

    @PostMapping("comprar")
    fun comprar(@RequestBody compra: Compra) = autorizadoraService.comprar(compra);

    @PostMapping("pagar")
    fun pagar(@RequestBody pagamento: Pagamento) = autorizadoraService.pagar(pagamento);
}