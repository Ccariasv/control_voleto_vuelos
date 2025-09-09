package com.airline.avion

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/aviones")
class AvionController(private val avionService: AvionService) {

    @GetMapping
    fun findAll(): List<AvionDto> = avionService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): AvionDto = avionService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: AvionCreateDto): AvionDto =
        avionService.create(dto)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: AvionCreateDto
    ): AvionDto = avionService.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = avionService.delete(id)
}
