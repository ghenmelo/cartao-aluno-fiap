package meu.projeto.teste.kotlin.models

data class Endereco(
    var rua: String?,
    val cidade: String,
    val estado: String,
    val pais: String,
    val cep: String
)
