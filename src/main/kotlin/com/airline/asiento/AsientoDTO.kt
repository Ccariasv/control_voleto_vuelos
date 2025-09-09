package com.airline.asiento

import com.airline.avion.Avion
import com.airline.claseasiento.ClaseAsiento
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * Data Transfer Object for [Asiento].
 */
data class AsientoDTO(
    val id: Long? = null,
    @field:NotNull val avionId: Long,
    @field:NotBlank val numeroAsiento: String,
    val claseId: Long? = null,
)

fun Asiento.toDTO(): AsientoDTO =
    AsientoDTO(
        id = id,
        avionId = avion.id!!,
        numeroAsiento = numeroAsiento,
        claseId = clase?.id,
    )

fun AsientoDTO.toEntity(avion: Avion, clase: ClaseAsiento?): Asiento =
    Asiento(
        id = id,
        avion = avion,
        numeroAsiento = numeroAsiento,
        clase = clase,
    )

