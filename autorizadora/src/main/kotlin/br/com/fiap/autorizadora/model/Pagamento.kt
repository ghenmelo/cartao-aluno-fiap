package br.com.fiap.autorizadora.model

import java.math.BigDecimal

class Pagamento(
    val identificadorAluno: String,
    val valor: BigDecimal,
    var descricao: String = "",
) {
}