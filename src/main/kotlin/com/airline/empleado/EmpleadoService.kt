package com.airline.empleado

import com.airline.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class EmpleadoService(private val empleadoRepository: EmpleadoRepository) {
    fun findAll(): List<Empleado> = empleadoRepository.findAll()

    fun findById(id: Long): Empleado =
        empleadoRepository.findById(id).orElseThrow { NotFoundException("Empleado $id no encontrado") }

    fun create(empleado: Empleado): Empleado = empleadoRepository.save(empleado)

    fun update(id: Long, empleado: Empleado): Empleado {
        if (!empleadoRepository.existsById(id)) {
            throw NotFoundException("Empleado $id no encontrado")
        }
        empleado.id = id
        return empleadoRepository.save(empleado)
    }

    fun delete(id: Long) {
        if (!empleadoRepository.existsById(id)) {
            throw NotFoundException("Empleado $id no encontrado")
        }
        empleadoRepository.deleteById(id)
    }
}
