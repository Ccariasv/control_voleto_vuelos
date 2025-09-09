package com.airline.avion

data class AvionDto(
    val id: Long,
    val modelo: String,
    val fabricante: String,
    val capacidadTotal: Int,
    val estado: String
)
