package com.airline.ruta

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

/** Input DTO for creating or updating Ruta. */
data class RutaCreateDto(
    @field:NotNull
    val origenId: Long,
    @field:NotNull
    val destinoId: Long,
    @field:NotNull @field:Positive
    val duracionAproxMin: Double,
    @field:NotNull @field:Positive
    val distanciaKm: Double
)
