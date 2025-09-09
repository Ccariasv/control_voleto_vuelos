package com.airline.ruta

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rutas")
class RutaController(private val rutaService: RutaService) {

    @GetMapping
    fun findAll(): List<RutaDto> = rutaService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): RutaDto = rutaService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: RutaCreateDto): RutaDto =
        rutaService.create(dto)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: RutaCreateDto
    ): RutaDto = rutaService.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = rutaService.delete(id)
}
