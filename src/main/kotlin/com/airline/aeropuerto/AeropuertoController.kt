package com.airline.aeropuerto

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/aeropuertos")
class AeropuertoController(private val aeropuertoService: AeropuertoService) {

    @GetMapping
    fun getAll(): List<AeropuertoDTO> =
        aeropuertoService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): AeropuertoDTO =
        aeropuertoService.findById(id).toDTO()

    @PostMapping
    fun create(@Valid @RequestBody aeropuertoDTO: AeropuertoDTO): ResponseEntity<AeropuertoDTO> {
        val saved = aeropuertoService.create(aeropuertoDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.toDTO())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody aeropuertoDTO: AeropuertoDTO
    ): ResponseEntity<AeropuertoDTO> {
        val updated = aeropuertoService.update(id, aeropuertoDTO.toEntity())
        return ResponseEntity.ok(updated.toDTO())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        aeropuertoService.delete(id)
        return ResponseEntity.noContent().build()
    }

    private fun Aeropuerto.toDTO() = AeropuertoDTO(
        id = id,
        nombre = requireNotNull(nombre),
        ciudad = requireNotNull(ciudad),
        pais = requireNotNull(pais),
        codigoIata = codigoIata
    )

    private fun AeropuertoDTO.toEntity() = Aeropuerto(
        id = id,
        nombre = nombre,
        ciudad = ciudad,
        pais = pais,
        codigoIata = codigoIata
    )
}

data class AeropuertoDTO(
    val id: Long? = null,
    @field:NotBlank
    val nombre: String,
    @field:NotBlank
    val ciudad: String,
    @field:NotBlank
    val pais: String,
    @field:NotBlank
    val codigoIata: String
)

