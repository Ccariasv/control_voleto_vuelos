package com.airline.boleto

import com.airline.asiento.AsientoService
import com.airline.pasajero.PasajeroService
import com.airline.vuelo.VueloService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/boletos")
class BoletoController(
    private val boletoService: BoletoService,
    private val vueloService: VueloService,
    private val pasajeroService: PasajeroService,
    private val asientoService: AsientoService,
) {
    @GetMapping
    fun getAll(): List<BoletoDTO> = boletoService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): BoletoDTO = boletoService.findById(id).toDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: BoletoDTO): BoletoDTO {
        val vuelo = vueloService.findById(dto.vueloId!!)
        val pasajero = pasajeroService.findById(dto.pasajeroId!!)
        val asiento = asientoService.findById(dto.asientoId!!)
        val saved = boletoService.create(dto.toEntity(vuelo, pasajero, asiento))
        return saved.toDTO()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: BoletoDTO): BoletoDTO {
        val vuelo = vueloService.findById(dto.vueloId!!)
        val pasajero = pasajeroService.findById(dto.pasajeroId!!)
        val asiento = asientoService.findById(dto.asientoId!!)
        val updated = boletoService.update(id, dto.toEntity(vuelo, pasajero, asiento))
        return updated.toDTO()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        boletoService.delete(id)
    }
}
