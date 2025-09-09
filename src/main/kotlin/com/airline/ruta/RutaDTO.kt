package com.airline.ruta

import jakarta.validation.constraints.NotNull

/**
 * DTO for Ruta entity.
 */
data class RutaDTO(
    val id: Long? = null,
    @field:NotNull
    val origenId: Long,
    @field:NotNull
    val destinoId: Long,
    @field:NotNull
    val duracionAproxMin: Double,
    @field:NotNull
    val distanciaKm: Double
)
