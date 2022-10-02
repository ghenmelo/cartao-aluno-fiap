package br.com.fiap.aluno.models

data class Endereco(
    var rua: String?,
    val cidade: String,
    val estado: String,
    val pais: String,
    val cep: String
)
