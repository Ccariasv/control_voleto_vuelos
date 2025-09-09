package com.airline.ruta

/** Output DTO for Ruta entity. */
data class RutaDto(
    val id: Long,
    val origenId: Long,
    val destinoId: Long,
    val duracionAproxMin: Double,
    val distanciaKm: Double
)
