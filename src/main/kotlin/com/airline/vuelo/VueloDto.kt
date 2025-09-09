package com.airline.vuelo

import java.time.LocalDateTime

data class VueloDto(
    val id: Long,
    val rutaId: Long,
    val avionId: Long,
    val salidaTs: LocalDateTime,
    val llegadaTs: LocalDateTime,
    val estado: String
)
