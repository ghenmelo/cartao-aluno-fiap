package br.com.fiap.autorizadora.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class Transacao (
    var id: String? = null,
    var identificador: String?,
    var valorTransacao: BigDecimal = BigDecimal.ZERO,
    var valorSaldoAluno: BigDecimal = BigDecimal.ZERO,
    var descricao: String = "",
    var tipoTransacao: TipoTransacao?,
    var dataTransacao: LocalDateTime = LocalDateTime.now()
)