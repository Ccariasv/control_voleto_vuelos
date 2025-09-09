package com.airline.turno

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class TurnoEmpleadoService(private val turnoEmpleadoRepository: TurnoEmpleadoRepository) {
    fun findAll(): List<TurnoEmpleado> = turnoEmpleadoRepository.findAll()

    fun findById(id: Long): TurnoEmpleado =
        turnoEmpleadoRepository.findById(id).orElseThrow { NotFoundException("TurnoEmpleado $id no encontrado") }

    fun create(turno: TurnoEmpleado): TurnoEmpleado = turnoEmpleadoRepository.save(turno)

    fun update(id: Long, turno: TurnoEmpleado): TurnoEmpleado {
        if (!turnoEmpleadoRepository.existsById(id)) {
            throw NotFoundException("TurnoEmpleado $id no encontrado")
        }
        turno.id = id
        return turnoEmpleadoRepository.save(turno)
    }

    fun delete(id: Long) {
        if (!turnoEmpleadoRepository.existsById(id)) {
            throw NotFoundException("TurnoEmpleado $id no encontrado")
        }
        turnoEmpleadoRepository.deleteById(id)
    }
}
