package com.airline.pasajero

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class PasajeroService(private val pasajeroRepository: PasajeroRepository) {
    fun findAll(): List<PasajeroDto> = pasajeroRepository.findAll().map { it.toDto() }

    fun findById(id: Long): PasajeroDto = findEntityById(id).toDto()

    fun findEntityById(id: Long): Pasajero =
        pasajeroRepository.findById(id).orElseThrow { NotFoundException("Pasajero $id no encontrado") }

    fun create(dto: PasajeroCreateDto): PasajeroDto {
        val entity = dto.toEntity()
        return pasajeroRepository.save(entity).toDto()
    }

    fun update(id: Long, dto: PasajeroCreateDto): PasajeroDto {
        if (!pasajeroRepository.existsById(id)) {
            throw NotFoundException("Pasajero $id no encontrado")
        }
        val entity = dto.toEntity()
        entity.id = id
        return pasajeroRepository.save(entity).toDto()
    }

    fun delete(id: Long) {
        if (!pasajeroRepository.existsById(id)) {
            throw NotFoundException("Pasajero $id no encontrado")
        }
        pasajeroRepository.deleteById(id)
    }

    private fun Pasajero.toDto() = PasajeroDto(
        id = id!!,
        nombres = nombres ?: "",
        apellidos = apellidos ?: "",
        email = email ?: "",
        telefono = telefono ?: "",
        documentoIdentidad = documentoIdentidad ?: ""
    )

    private fun PasajeroCreateDto.toEntity() = Pasajero(
        nombres = nombres,
        apellidos = apellidos,
        email = email,
        telefono = telefono,
        documentoIdentidad = documentoIdentidad
    )
}
