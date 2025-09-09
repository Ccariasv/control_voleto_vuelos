package com.airline.asiento

import com.airline.avion.AvionService
import com.airline.claseasiento.ClaseAsientoService
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
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/asientos")
class AsientoController(
    private val asientoService: AsientoService,
    private val avionService: AvionService,
    private val claseAsientoService: ClaseAsientoService,
) {
    @GetMapping
    fun getAll(): List<AsientoDTO> =
        asientoService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): AsientoDTO =
        asientoService.findById(id).toDTO()

    @PostMapping
    fun create(@Valid @RequestBody dto: AsientoDTO): ResponseEntity<AsientoDTO> {
        val avion = avionService.findById(dto.avionId)
        val clase = dto.claseId?.let { claseAsientoService.findById(it) }
        val created = asientoService.create(dto.toEntity(avion, clase))
        return ResponseEntity.status(HttpStatus.CREATED).body(created.toDTO())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: AsientoDTO,
    ): ResponseEntity<Void> {
        val avion = avionService.findById(dto.avionId)
        val clase = dto.claseId?.let { claseAsientoService.findById(it) }
        asientoService.update(id, dto.toEntity(avion, clase))
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        asientoService.delete(id)
        return ResponseEntity.noContent().build()
    }
}

