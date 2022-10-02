package br.com.fiap.autorizadora.model

import java.math.BigDecimal

data class Compra(
    val identificadorAluno: String,
    val valor: BigDecimal,
    var descricao: String = "",
) {
}