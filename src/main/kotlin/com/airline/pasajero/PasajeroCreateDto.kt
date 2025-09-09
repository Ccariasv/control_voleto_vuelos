package com.airline.pasajero

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class PasajeroCreateDto(
    @field:NotBlank(message = "El nombre no puede estar vacío")
    val nombres: String,
    @field:NotBlank(message = "El apellido no puede estar vacío")
    val apellidos: String,
    @field:NotBlank(message = "El email no puede estar vacío")
    @field:Email(message = "El email debe tener un formato válido")
    val email: String,
    @field:NotBlank(message = "El teléfono no puede estar vacío")
    val telefono: String,
    @field:NotBlank(message = "El documento de identidad no puede estar vacío")
    val documentoIdentidad: String,
)

