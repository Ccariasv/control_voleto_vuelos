package com.airline.equipaje

import com.airline.checkin.Checkin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

/**
 * Data transfer object for [Equipaje]
 */
data class EquipajeDTO(
    val id: Long? = null,
    @field:NotNull(message = "idCheckin es requerido")
    val idCheckin: Long?,
    @field:NotBlank(message = "descripcion es requerida")
    val descripcion: String?,
    @field:Positive(message = "peso debe ser mayor a 0")
    val peso: BigDecimal?
)

fun Equipaje.toDTO(): EquipajeDTO = EquipajeDTO(
    id = this.id,
    idCheckin = this.checkin.id,
    descripcion = this.descripcion,
    peso = this.peso
)

fun EquipajeDTO.toEntity(checkin: Checkin): Equipaje = Equipaje(
    id = this.id,
    checkin = checkin,
    descripcion = this.descripcion,
    peso = this.peso
)

