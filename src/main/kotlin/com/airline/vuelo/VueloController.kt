package com.airline.vuelo

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vuelos")
class VueloController(private val vueloService: VueloService) {

    @GetMapping
    fun findAll(): List<VueloDto> = vueloService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): VueloDto = vueloService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: VueloCreateDto): VueloDto =
        vueloService.create(dto)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: VueloCreateDto,
    ): VueloDto = vueloService.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = vueloService.delete(id)
}
