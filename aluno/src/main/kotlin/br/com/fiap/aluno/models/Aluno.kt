package br.com.fiap.aluno.models

import java.math.BigDecimal

data class Aluno(
    var id: String? = null,
    var nome: String?,
    var identificador: String?,
    var codigoMatricula: String?,
    var limite: BigDecimal = BigDecimal.ZERO,
    var gasto: BigDecimal = BigDecimal.ZERO
)