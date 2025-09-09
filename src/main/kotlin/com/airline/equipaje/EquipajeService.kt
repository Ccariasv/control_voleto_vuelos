package com.airline.equipaje

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class EquipajeService(private val equipajeRepository: EquipajeRepository) {
    fun findAll(): List<Equipaje> = equipajeRepository.findAll()

    fun findById(id: Long): Equipaje =
        equipajeRepository.findById(id).orElseThrow { NotFoundException("Equipaje $id no encontrado") }

    fun create(equipaje: Equipaje): Equipaje = equipajeRepository.save(equipaje)

    fun update(id: Long, equipaje: Equipaje): Equipaje {
        if (!equipajeRepository.existsById(id)) {
            throw NotFoundException("Equipaje $id no encontrado")
        }
        equipaje.id = id
        return equipajeRepository.save(equipaje)
    }

    fun delete(id: Long) {
        if (!equipajeRepository.existsById(id)) {
            throw NotFoundException("Equipaje $id no encontrado")
        }
        equipajeRepository.deleteById(id)
    }
}
