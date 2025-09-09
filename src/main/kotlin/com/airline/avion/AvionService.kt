package com.airline.avion

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class AvionService(private val avionRepository: AvionRepository) {
    fun findAll(): List<Avion> = avionRepository.findAll()

    fun findById(id: Long): Avion =
        avionRepository.findById(id).orElseThrow { NotFoundException("Avion $id no encontrado") }

    fun create(avion: Avion): Avion = avionRepository.save(avion)

    fun update(id: Long, avion: Avion): Avion {
        if (!avionRepository.existsById(id)) {
            throw NotFoundException("Avion $id no encontrado")
        }
        avion.id = id
        return avionRepository.save(avion)
    }

    fun delete(id: Long) {
        if (!avionRepository.existsById(id)) {
            throw NotFoundException("Avion $id no encontrado")
        }
        avionRepository.deleteById(id)
    }
}
