package br.com.fiap.autorizadora.controller

import br.com.fiap.autorizadora.model.Compra
import br.com.fiap.autorizadora.model.Pagamento
import br.com.fiap.autorizadora.service.AutorizadoraService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pessoas")
class TransacaoController (val autorizadoraService: AutorizadoraService){

    fun comprar(@RequestBody compra: Compra) = autorizadoraService.comprar(compra);

    fun pagar(@RequestBody pagamento: Pagamento) = autorizadoraService.pagar(pagamento);
}