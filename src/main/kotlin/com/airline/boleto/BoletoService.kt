package com.airline.boleto

import com.airline.asiento.AsientoService
import com.airline.exception.BusinessException
import com.airline.exception.NotFoundException
import com.airline.pasajero.PasajeroService
import com.airline.vuelo.VueloService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class BoletoService(
    private val boletoRepository: BoletoRepository,
    private val vueloService: VueloService,
    private val pasajeroService: PasajeroService,
    private val asientoService: AsientoService,
) {
    fun findAll(): List<BoletoDto> = boletoRepository.findAll().map { it.toDto() }

    fun findById(id: Long): BoletoDto = findEntityById(id).toDto()

    fun findEntityById(id: Long): Boleto =
        boletoRepository.findById(id).orElseThrow { NotFoundException("Boleto $id no encontrado") }

    fun create(dto: BoletoCreateDto): BoletoDto {
        val entity = dto.toEntity()
        if (boletoRepository.existsByVueloIdAndAsientoId(entity.vuelo.id, entity.asiento.id)) {
            throw BusinessException("El asiento ya está vendido para este vuelo")
        }
        return boletoRepository.save(entity).toDto()
    }

    fun update(id: Long, dto: BoletoCreateDto): BoletoDto {
        val entity = dto.toEntity()
        val existing = findEntityById(id)
        if (entity.vuelo.id != existing.vuelo.id || entity.asiento.id != existing.asiento.id) {
            if (boletoRepository.existsByVueloIdAndAsientoId(entity.vuelo.id, entity.asiento.id)) {
                throw BusinessException("El asiento ya está vendido para este vuelo")
            }
        }
        entity.id = id
        return boletoRepository.save(entity).toDto()
    }

    fun delete(id: Long) {
        if (!boletoRepository.existsById(id)) {
            throw NotFoundException("Boleto $id no encontrado")
        }
        boletoRepository.deleteById(id)
    }

    private fun Boleto.toDto() = BoletoDto(
        id = id!!,
        vueloId = vuelo.id!!,
        pasajeroId = pasajero.id!!,
        asientoId = asiento.id!!,
        precio = precio ?: BigDecimal.ZERO,
        fechaEmision = fechaEmision ?: LocalDate.MIN,
        estado = estado ?: ""
    )

    private fun BoletoCreateDto.toEntity(): Boleto {
        val vuelo = vueloService.findEntityById(vueloId)
        val pasajero = pasajeroService.findEntityById(pasajeroId)
        val asiento = asientoService.findById(asientoId)
        return Boleto(
            vuelo = vuelo,
            pasajero = pasajero,
            asiento = asiento,
            precio = precio,
            fechaEmision = fechaEmision,
            estado = estado
        )
    }
}
