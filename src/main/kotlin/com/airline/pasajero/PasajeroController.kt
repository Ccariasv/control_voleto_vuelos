package com.airline.pasajero

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pasajeros")
class PasajeroController(private val pasajeroService: PasajeroService) {

    @GetMapping
    fun getAll(): List<PasajeroDto> = pasajeroService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PasajeroDto = pasajeroService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: PasajeroCreateDto): PasajeroDto =
        pasajeroService.create(dto)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: PasajeroCreateDto,
    ): PasajeroDto = pasajeroService.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = pasajeroService.delete(id)
}
