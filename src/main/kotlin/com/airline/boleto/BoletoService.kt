package com.airline.boleto

import com.airline.exception.BusinessException
import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class BoletoService(private val boletoRepository: BoletoRepository) {
    fun findAll(): List<Boleto> = boletoRepository.findAll()

    fun findById(id: Long): Boleto =
        boletoRepository.findById(id).orElseThrow { NotFoundException("Boleto $id no encontrado") }

    fun create(boleto: Boleto): Boleto {
        if (boletoRepository.existsByVueloIdAndAsientoId(boleto.vuelo.id, boleto.asiento.id)) {
            throw BusinessException("El asiento ya está vendido para este vuelo")
        }
        return boletoRepository.save(boleto)
    }

    fun update(id: Long, boleto: Boleto): Boleto {
        val existing = findById(id)
        if (boleto.vuelo.id != existing.vuelo.id || boleto.asiento.id != existing.asiento.id) {
            if (boletoRepository.existsByVueloIdAndAsientoId(boleto.vuelo.id, boleto.asiento.id)) {
                throw BusinessException("El asiento ya está vendido para este vuelo")
            }
        }
        boleto.id = id
        return boletoRepository.save(boleto)
    }

    fun delete(id: Long) {
        if (!boletoRepository.existsById(id)) {
            throw NotFoundException("Boleto $id no encontrado")
        }
        boletoRepository.deleteById(id)
    }
}
