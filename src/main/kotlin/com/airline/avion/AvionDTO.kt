package com.airline.avion

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * DTO for Avion entity.
 */
data class AvionDTO(
    val id: Long? = null,
    @field:NotBlank
    val modelo: String,
    @field:NotBlank
    val fabricante: String,
    @field:NotNull
    val capacidadTotal: Int,
    @field:NotBlank
    val estado: String
)
