package com.airline.asiento

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class AsientoService(private val asientoRepository: AsientoRepository) {
    fun findAll(): List<Asiento> = asientoRepository.findAll()

    fun findById(id: Long): Asiento =
        asientoRepository.findById(id).orElseThrow { NotFoundException("Asiento $id no encontrado") }

    fun create(asiento: Asiento): Asiento = asientoRepository.save(asiento)

    fun update(id: Long, asiento: Asiento): Asiento {
        if (!asientoRepository.existsById(id)) {
            throw NotFoundException("Asiento $id no encontrado")
        }
        asiento.id = id
        return asientoRepository.save(asiento)
    }

    fun delete(id: Long) {
        if (!asientoRepository.existsById(id)) {
            throw NotFoundException("Asiento $id no encontrado")
        }
        asientoRepository.deleteById(id)
    }
}
