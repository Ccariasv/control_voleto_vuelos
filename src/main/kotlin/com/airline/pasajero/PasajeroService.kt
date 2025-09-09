package com.airline.pasajero

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class PasajeroService(private val pasajeroRepository: PasajeroRepository) {
    fun findAll(): List<Pasajero> = pasajeroRepository.findAll()

    fun findById(id: Long): Pasajero =
        pasajeroRepository.findById(id).orElseThrow { NotFoundException("Pasajero $id no encontrado") }

    fun create(pasajero: Pasajero): Pasajero = pasajeroRepository.save(pasajero)

    fun update(id: Long, pasajero: Pasajero): Pasajero {
        if (!pasajeroRepository.existsById(id)) {
            throw NotFoundException("Pasajero $id no encontrado")
        }
        pasajero.id = id
        return pasajeroRepository.save(pasajero)
    }

    fun delete(id: Long) {
        if (!pasajeroRepository.existsById(id)) {
            throw NotFoundException("Pasajero $id no encontrado")
        }
        pasajeroRepository.deleteById(id)
    }
}
