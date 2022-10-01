package meu.projeto.teste.kotlin.controller

import meu.projeto.teste.kotlin.models.Aluno
import meu.projeto.teste.kotlin.service.AlunoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("aluno")
class AlunoController (val alunoService: AlunoService) {

    @GetMapping
    fun buscar() :List<Aluno> {
        return alunoService.buscar();
    }

    @GetMapping("{identificadorAluno}")
    fun buscaAluno(@PathVariable identificadorAluno: String) :Aluno = alunoService.buscarAluno(identificadorAluno)

    @PostMapping
    fun criar(@RequestBody aluno: Aluno) = alunoService.criar(aluno)

    @PutMapping("{identificadorAluno}")
    fun atualizar(@PathVariable identificadorAluno: String, @RequestBody aluno: Aluno) = alunoService.atualizar(identificadorAluno, aluno)

    @PatchMapping("atualiza-saldo/{identificadorAluno}")
    fun atualizarSaldo(@PathVariable identificadorAluno: String, @RequestBody aluno: Aluno) = alunoService.atualizar(identificadorAluno, aluno)

    @DeleteMapping("{identificadorAluno}")
    fun deletar(@PathVariable identificadorAluno: String) = alunoService.deletar(identificadorAluno)
}