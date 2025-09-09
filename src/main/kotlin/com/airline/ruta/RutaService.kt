package com.airline.ruta

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class RutaService(private val rutaRepository: RutaRepository) {
    fun findAll(): List<Ruta> = rutaRepository.findAll()

    fun findById(id: Long): Ruta =
        rutaRepository.findById(id).orElseThrow { NotFoundException("Ruta $id no encontrada") }

    fun create(ruta: Ruta): Ruta = rutaRepository.save(ruta)

    fun update(id: Long, ruta: Ruta): Ruta {
        if (!rutaRepository.existsById(id)) {
            throw NotFoundException("Ruta $id no encontrada")
        }
        ruta.id = id
        return rutaRepository.save(ruta)
    }

    fun delete(id: Long) {
        if (!rutaRepository.existsById(id)) {
            throw NotFoundException("Ruta $id no encontrada")
        }
        rutaRepository.deleteById(id)
    }
}
