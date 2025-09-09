package com.airline.vuelo

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class VueloCreateDto(
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
