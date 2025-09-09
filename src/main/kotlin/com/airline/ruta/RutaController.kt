package com.airline.ruta

import com.airline.aeropuerto.AeropuertoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rutas")
class RutaController(
    private val rutaService: RutaService,
    private val aeropuertoService: AeropuertoService
) {
    @GetMapping
    fun findAll(): List<RutaDTO> = rutaService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): RutaDTO = rutaService.findById(id).toDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: RutaDTO): RutaDTO =
        rutaService.create(dto.toEntity()).toDTO()

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: RutaDTO
    ): RutaDTO = rutaService.update(id, dto.toEntity()).toDTO()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = rutaService.delete(id)

    // Mapping helpers
    private fun Ruta.toDTO() = RutaDTO(
        id = id,
        origenId = origen.id!!,
        destinoId = destino.id!!,
        duracionAproxMin = duracionAproxMin ?: 0.0,
        distanciaKm = distanciaKm ?: 0.0
    )

    private fun RutaDTO.toEntity() = Ruta(
        id = id,
        origen = aeropuertoService.findById(origenId),
        destino = aeropuertoService.findById(destinoId),
        duracionAproxMin = duracionAproxMin,
        distanciaKm = distanciaKm
    )
}
