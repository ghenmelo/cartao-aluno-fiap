package br.com.fiap.autorizadora.model

import java.math.BigDecimal

data class Aluno(
    val id: String? = null,
    var nome: String?,
    var identificador: String,
    var codigoMatricula: String,
    var limite: BigDecimal,
    var gasto: BigDecimal
)