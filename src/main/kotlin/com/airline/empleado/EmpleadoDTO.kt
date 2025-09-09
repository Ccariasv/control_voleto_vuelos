package com.airline.empleado

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

/**
 * Data transfer object for [Empleado].
 */
data class EmpleadoDTO(
    val id: Long? = null,
    @field:NotBlank val nombres: String,
    @field:NotBlank val apellidos: String,
    @field:NotBlank val puesto: String,
    @field:Email val email: String,
    @field:NotBlank val telefono: String
)

fun Empleado.toDTO(): EmpleadoDTO = EmpleadoDTO(
    id = id,
    nombres = nombres ?: "",
    apellidos = apellidos ?: "",
    puesto = puesto ?: "",
    email = email ?: "",
    telefono = telefono ?: ""
)

fun EmpleadoDTO.toEntity(): Empleado = Empleado(
    id = id,
    nombres = nombres,
    apellidos = apellidos,
    puesto = puesto,
    email = email,
    telefono = telefono
)
