package com.airline.vuelo

import com.airline.avion.AvionService
import com.airline.ruta.RutaService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/vuelos")
class VueloController(
    private val vueloService: VueloService,
    private val rutaService: RutaService,
    private val avionService: AvionService
) {
    @GetMapping
    fun findAll(): List<VueloDTO> = vueloService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): VueloDTO = vueloService.findById(id).toDTO()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody dto: VueloDTO): VueloDTO =
        vueloService.create(dto.toEntity()).toDTO()

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: VueloDTO
    ): VueloDTO = vueloService.update(id, dto.toEntity()).toDTO()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = vueloService.delete(id)

    // Mapping helpers
    private fun Vuelo.toDTO() = VueloDTO(
        id = id,
        rutaId = ruta.id!!,
        avionId = avion.id!!,
        salidaTs = salidaTs ?: java.time.LocalDateTime.MIN,
        llegadaTs = llegadaTs ?: java.time.LocalDateTime.MIN,
        estado = estado ?: ""
    )

    private fun VueloDTO.toEntity() = Vuelo(
        id = id,
        ruta = rutaService.findEntityById(rutaId),
        avion = avionService.findById(avionId),
        salidaTs = salidaTs,
        llegadaTs = llegadaTs,
        estado = estado
    )
}
