package br.com.fiap.aluno.controller

import br.com.fiap.aluno.models.Transacao
import br.com.fiap.aluno.service.AlunoService
import br.com.fiap.aluno.service.TransacaoService
import org.springframework.web.bind.annotation.*

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("transacao/{identificadorAluno}")
class TransacaoController (val alunoService: AlunoService, val transacaoService: TransacaoService) {

    @GetMapping()
    fun buscarTransacoes(@PathVariable identificadorAluno: String) :List<Transacao> = transacaoService.buscarTransacoes(identificadorAluno)

    @PutMapping()
    fun atualizarSaldo(@PathVariable identificadorAluno: String, @RequestBody transacao: Transacao) = alunoService.atualizarSaldo(identificadorAluno, transacao)


    @GetMapping("/downloadCsv")
    fun buscarTransacoes(@PathVariable identificadorAluno: String, httpServletResponse :HttpServletResponse) {
        httpServletResponse.setContentType("text/csv");
        httpServletResponse.addHeader("Content-Disposition",""" attachment; filename=\"transacoes_aluno_$identificadorAluno.csv\"""");
        transacaoService.downloadToCsv(identificadorAluno, httpServletResponse.writer)
    }
}