package com.airline.ruta

import com.airline.aeropuerto.AeropuertoService
import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class RutaService(
    private val rutaRepository: RutaRepository,
    private val aeropuertoService: AeropuertoService
) {
    fun findAll(): List<RutaDto> = rutaRepository.findAll().map { it.toDto() }

    fun findById(id: Long): RutaDto = findEntityById(id).toDto()

    fun findEntityById(id: Long): Ruta =
        rutaRepository.findById(id).orElseThrow { NotFoundException("Ruta $id no encontrada") }

    fun create(dto: RutaCreateDto): RutaDto {
        val entity = dto.toEntity()
        return rutaRepository.save(entity).toDto()
    }

    fun update(id: Long, dto: RutaCreateDto): RutaDto {
        if (!rutaRepository.existsById(id)) {
            throw NotFoundException("Ruta $id no encontrada")
        }
        val entity = dto.toEntity()
        entity.id = id
        return rutaRepository.save(entity).toDto()
    }

    fun delete(id: Long) {
        if (!rutaRepository.existsById(id)) {
            throw NotFoundException("Ruta $id no encontrada")
        }
        rutaRepository.deleteById(id)
    }

    private fun Ruta.toDto() = RutaDto(
        id = id!!,
        origenId = origen.id!!,
        destinoId = destino.id!!,
        duracionAproxMin = duracionAproxMin ?: 0.0,
        distanciaKm = distanciaKm ?: 0.0
    )

    private fun RutaCreateDto.toEntity() = Ruta(
        origen = aeropuertoService.findById(origenId),
        destino = aeropuertoService.findById(destinoId),
        duracionAproxMin = duracionAproxMin,
        distanciaKm = distanciaKm
    )
}
