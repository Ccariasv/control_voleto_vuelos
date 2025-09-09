package com.airline.boleto

import com.airline.asiento.Asiento
import com.airline.pasajero.Pasajero
import com.airline.vuelo.Vuelo
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

/**
 * Data transfer object for [Boleto].
 */
data class BoletoDTO(
    val id: Long? = null,
    @field:NotNull val vueloId: Long? = null,
    @field:NotNull val pasajeroId: Long? = null,
    @field:NotNull val asientoId: Long? = null,
    @field:NotNull @field:DecimalMin("0.0", inclusive = false) val precio: BigDecimal? = null,
    @field:NotNull val fechaEmision: LocalDate? = null,
    @field:NotBlank val estado: String? = null,
)

fun Boleto.toDTO() = BoletoDTO(
    id = id,
    vueloId = vuelo.id,
    pasajeroId = pasajero.id,
    asientoId = asiento.id,
    precio = precio,
    fechaEmision = fechaEmision,
    estado = estado
)

fun BoletoDTO.toEntity(vuelo: Vuelo, pasajero: Pasajero, asiento: Asiento) = Boleto(
    id = id,
    vuelo = vuelo,
    pasajero = pasajero,
    asiento = asiento,
    precio = precio,
    fechaEmision = fechaEmision,
    estado = estado
)
