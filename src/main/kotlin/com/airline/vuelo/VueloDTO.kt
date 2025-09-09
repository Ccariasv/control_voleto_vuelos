package com.airline.vuelo

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

/**
 * DTO for Vuelo entity.
 */
data class VueloDTO(
    val id: Long? = null,
    @field:NotNull
    val rutaId: Long,
    @field:NotNull
    val avionId: Long,
    @field:NotNull
    val salidaTs: LocalDateTime,
    @field:NotNull
    val llegadaTs: LocalDateTime,
    @field:NotBlank
    val estado: String
)
