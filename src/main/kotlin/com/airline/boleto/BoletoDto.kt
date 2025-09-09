package com.airline.boleto

import java.math.BigDecimal
import java.time.LocalDate

data class BoletoDto(
    val id: Long,
    val vueloId: Long,
    val pasajeroId: Long,
    val asientoId: Long,
    val precio: BigDecimal,
    val fechaEmision: LocalDate,
    val estado: String,
)
