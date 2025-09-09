package com.airline.avion

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AvionCreateDto(
    @field:NotBlank
    val modelo: String,
    @field:NotBlank
    val fabricante: String,
    @field:NotNull
    val capacidadTotal: Int,
    @field:NotBlank
    val estado: String
)
