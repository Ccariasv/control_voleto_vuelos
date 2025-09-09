package com.airline.avion

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/aviones")
class AvionController(private val avionService: AvionService) {

    @GetMapping
    fun findAll(): List<AvionDTO> = avionService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): AvionDTO = avionService.findById(id).toDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody avionDTO: AvionDTO): AvionDTO =
        avionService.create(avionDTO.toEntity()).toDTO()

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody avionDTO: AvionDTO
    ): AvionDTO = avionService.update(id, avionDTO.toEntity()).toDTO()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = avionService.delete(id)

    // Mapping helpers
    private fun Avion.toDTO() = AvionDTO(id, modelo ?: "", fabricante ?: "", capacidadTotal ?: 0, estado ?: "")

    private fun AvionDTO.toEntity() = Avion(
        id = id,
        modelo = modelo,
        fabricante = fabricante,
        capacidadTotal = capacidadTotal,
        estado = estado
    )
}
