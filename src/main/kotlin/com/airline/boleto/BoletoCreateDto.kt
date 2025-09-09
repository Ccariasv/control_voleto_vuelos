package com.airline.boleto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class BoletoCreateDto(
    @field:NotNull val vueloId: Long,
    @field:NotNull val pasajeroId: Long,
    @field:NotNull val asientoId: Long,
    @field:NotNull @field:DecimalMin("0.0", inclusive = false) val precio: BigDecimal,
    @field:NotNull val fechaEmision: LocalDate,
    @field:NotBlank val estado: String,
)
