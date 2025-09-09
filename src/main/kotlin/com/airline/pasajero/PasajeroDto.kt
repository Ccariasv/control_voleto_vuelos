package com.airline.pasajero

data class PasajeroDto(
    val id: Long,
    val nombres: String,
    val apellidos: String,
    val email: String,
    val telefono: String,
    val documentoIdentidad: String,
)
