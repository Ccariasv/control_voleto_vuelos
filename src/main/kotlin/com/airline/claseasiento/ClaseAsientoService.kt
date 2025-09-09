package com.airline.claseasiento

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class ClaseAsientoService(private val claseAsientoRepository: ClaseAsientoRepository) {
    fun findAll(): List<ClaseAsiento> = claseAsientoRepository.findAll()

    fun findById(id: Long): ClaseAsiento =
        claseAsientoRepository.findById(id).orElseThrow { NotFoundException("ClaseAsiento $id no encontrado") }

    fun create(clase: ClaseAsiento): ClaseAsiento = claseAsientoRepository.save(clase)

    fun update(id: Long, clase: ClaseAsiento): ClaseAsiento {
        if (!claseAsientoRepository.existsById(id)) {
            throw NotFoundException("ClaseAsiento $id no encontrado")
        }
        clase.id = id
        return claseAsientoRepository.save(clase)
    }

    fun delete(id: Long) {
        if (!claseAsientoRepository.existsById(id)) {
            throw NotFoundException("ClaseAsiento $id no encontrado")
        }
        claseAsientoRepository.deleteById(id)
    }
}
