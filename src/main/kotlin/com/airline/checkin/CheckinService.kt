package com.airline.checkin

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class CheckinService(private val checkinRepository: CheckinRepository) {
    fun findAll(): List<Checkin> = checkinRepository.findAll()

    fun findById(id: Long): Checkin =
        checkinRepository.findById(id).orElseThrow { NotFoundException("Checkin $id no encontrado") }

    fun create(checkin: Checkin): Checkin = checkinRepository.save(checkin)

    fun update(id: Long, checkin: Checkin): Checkin {
        if (!checkinRepository.existsById(id)) {
            throw NotFoundException("Checkin $id no encontrado")
        }
        checkin.id = id
        return checkinRepository.save(checkin)
    }

    fun delete(id: Long) {
        if (!checkinRepository.existsById(id)) {
            throw NotFoundException("Checkin $id no encontrado")
        }
        checkinRepository.deleteById(id)
    }
}
