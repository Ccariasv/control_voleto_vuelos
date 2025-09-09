package com.airline.pago

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class PagoService(private val pagoRepository: PagoRepository) {
    fun findAll(): List<Pago> = pagoRepository.findAll()

    fun findById(id: Long): Pago =
        pagoRepository.findById(id).orElseThrow { NotFoundException("Pago $id no encontrado") }

    fun create(pago: Pago): Pago = pagoRepository.save(pago)

    fun update(id: Long, pago: Pago): Pago {
        if (!pagoRepository.existsById(id)) {
            throw NotFoundException("Pago $id no encontrado")
        }
        pago.id = id
        return pagoRepository.save(pago)
    }

    fun delete(id: Long) {
        if (!pagoRepository.existsById(id)) {
            throw NotFoundException("Pago $id no encontrado")
        }
        pagoRepository.deleteById(id)
    }
}
