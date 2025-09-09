package com.airline.pasajero

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
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
@RequestMapping("/pasajeros")
class PasajeroController(private val pasajeroService: PasajeroService) {

    @GetMapping
    fun getAll(): List<PasajeroDTO> = pasajeroService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PasajeroDTO = pasajeroService.findById(id).toDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody pasajeroDTO: PasajeroDTO): PasajeroDTO =
        pasajeroService.create(pasajeroDTO.toEntity()).toDTO()

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody pasajeroDTO: PasajeroDTO,
    ): PasajeroDTO = pasajeroService.update(id, pasajeroDTO.toEntity()).toDTO()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        pasajeroService.delete(id)
    }
}

