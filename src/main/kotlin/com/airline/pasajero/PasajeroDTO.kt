package com.airline.pasajero

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

/**
 * Data Transfer Object for [Pasajero].
 */
data class PasajeroDTO(
    val id: Long? = null,
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

/**
 * Extension function to convert a [Pasajero] entity to a [PasajeroDTO].
 */
fun Pasajero.toDTO() = PasajeroDTO(
    id = id,
    nombres = nombres ?: "",
    apellidos = apellidos ?: "",
    email = email ?: "",
    telefono = telefono ?: "",
    documentoIdentidad = documentoIdentidad ?: "",
)

/**
 * Extension function to convert a [PasajeroDTO] to a [Pasajero] entity.
 */
fun PasajeroDTO.toEntity() = Pasajero(
    id = id,
    nombres = nombres,
    apellidos = apellidos,
    email = email,
    telefono = telefono,
    documentoIdentidad = documentoIdentidad,
)

