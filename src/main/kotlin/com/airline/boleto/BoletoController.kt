package com.airline.boleto

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/boletos")
class BoletoController(private val boletoService: BoletoService) {
    @GetMapping
    fun getAll(): List<BoletoDto> = boletoService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): BoletoDto = boletoService.findById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: BoletoCreateDto): BoletoDto =
        boletoService.create(dto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: BoletoCreateDto): BoletoDto =
        boletoService.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = boletoService.delete(id)
}
