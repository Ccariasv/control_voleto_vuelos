package com.airline.avion

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class AvionService(private val avionRepository: AvionRepository) {
    fun findAll(): List<AvionDto> = avionRepository.findAll().map { it.toDto() }

    fun findById(id: Long): AvionDto = findEntityById(id).toDto()

    fun findEntityById(id: Long): Avion =
        avionRepository.findById(id).orElseThrow { NotFoundException("Avion $id no encontrado") }

    fun create(dto: AvionCreateDto): AvionDto {
        val entity = dto.toEntity()
        return avionRepository.save(entity).toDto()
    }

    fun update(id: Long, dto: AvionCreateDto): AvionDto {
        if (!avionRepository.existsById(id)) {
            throw NotFoundException("Avion $id no encontrado")
        }
        val entity = dto.toEntity()
        entity.id = id
        return avionRepository.save(entity).toDto()
    }

    fun delete(id: Long) {
        if (!avionRepository.existsById(id)) {
            throw NotFoundException("Avion $id no encontrado")
        }
        avionRepository.deleteById(id)
    }

    private fun Avion.toDto() = AvionDto(
        id = id!!,
        modelo = modelo ?: "",
        fabricante = fabricante ?: "",
        capacidadTotal = capacidadTotal ?: 0,
        estado = estado ?: ""
    )

    private fun AvionCreateDto.toEntity() = Avion(
        modelo = modelo,
        fabricante = fabricante,
        capacidadTotal = capacidadTotal,
        estado = estado
    )
}
