package com.airline.empleado

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/empleados")
class EmpleadoController(private val empleadoService: EmpleadoService) {

    @GetMapping
    fun getAll(): List<EmpleadoDTO> = empleadoService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): EmpleadoDTO = empleadoService.findById(id).toDTO()

    @PostMapping
    fun create(@Valid @RequestBody empleadoDTO: EmpleadoDTO): ResponseEntity<EmpleadoDTO> {
        val saved = empleadoService.create(empleadoDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.toDTO())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody empleadoDTO: EmpleadoDTO
    ): EmpleadoDTO = empleadoService.update(id, empleadoDTO.toEntity()).toDTO()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        empleadoService.delete(id)
    }
}
