package br.com.fiap.aluno.controller

import br.com.fiap.aluno.models.Aluno
import br.com.fiap.aluno.models.Transacao
import br.com.fiap.aluno.service.AlunoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("aluno")
class AlunoController (val alunoService: AlunoService) {

    @GetMapping
    fun buscar() :List<Aluno> = alunoService.buscar()

    @GetMapping("{identificadorAluno}")
    fun buscaAluno(@PathVariable identificadorAluno: String) : Aluno = alunoService.buscarAluno(identificadorAluno)

    @PostMapping
    fun criar(@RequestBody aluno: Aluno) = alunoService.criar(aluno)

    @PutMapping("{identificadorAluno}")
    fun atualizar(@PathVariable identificadorAluno: String, @RequestBody aluno: Aluno) = alunoService.atualizar(identificadorAluno, aluno)

    @DeleteMapping("{identificadorAluno}")
    fun deletar(@PathVariable identificadorAluno: String) = alunoService.deletar(identificadorAluno)
}