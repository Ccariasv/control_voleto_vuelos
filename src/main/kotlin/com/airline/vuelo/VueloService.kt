package com.airline.vuelo

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class VueloService(private val vueloRepository: VueloRepository) {
    fun findAll(): List<Vuelo> = vueloRepository.findAll()

    fun findById(id: Long): Vuelo =
        vueloRepository.findById(id).orElseThrow { NotFoundException("Vuelo $id no encontrado") }

    fun create(vuelo: Vuelo): Vuelo = vueloRepository.save(vuelo)

    fun update(id: Long, vuelo: Vuelo): Vuelo {
        if (!vueloRepository.existsById(id)) {
            throw NotFoundException("Vuelo $id no encontrado")
        }
        vuelo.id = id
        return vueloRepository.save(vuelo)
    }

    fun delete(id: Long) {
        if (!vueloRepository.existsById(id)) {
            throw NotFoundException("Vuelo $id no encontrado")
        }
        vueloRepository.deleteById(id)
    }
}
