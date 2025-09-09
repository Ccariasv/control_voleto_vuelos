package com.airline.aeropuerto

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class AeropuertoService(private val aeropuertoRepository: AeropuertoRepository) {
    fun findAll(): List<Aeropuerto> = aeropuertoRepository.findAll()

    fun findById(id: Long): Aeropuerto =
        aeropuertoRepository.findById(id).orElseThrow { NotFoundException("Aeropuerto $id no encontrado") }

    fun create(aeropuerto: Aeropuerto): Aeropuerto = aeropuertoRepository.save(aeropuerto)

    fun update(id: Long, aeropuerto: Aeropuerto): Aeropuerto {
        if (!aeropuertoRepository.existsById(id)) {
            throw NotFoundException("Aeropuerto $id no encontrado")
        }
        aeropuerto.id = id
        return aeropuertoRepository.save(aeropuerto)
    }

    fun delete(id: Long) {
        if (!aeropuertoRepository.existsById(id)) {
            throw NotFoundException("Aeropuerto $id no encontrado")
        }
        aeropuertoRepository.deleteById(id)
    }
}
