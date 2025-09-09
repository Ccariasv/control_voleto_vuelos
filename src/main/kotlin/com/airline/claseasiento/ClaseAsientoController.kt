package com.airline.claseasiento

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clases-asiento")
class ClaseAsientoController(private val claseAsientoService: ClaseAsientoService) {

    @GetMapping
    fun getAll(): List<ClaseAsientoResponse> =
        claseAsientoService.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ClaseAsientoResponse =
        claseAsientoService.findById(id).toResponse()

    @PostMapping
    fun create(@Valid @RequestBody request: ClaseAsientoRequest): ResponseEntity<ClaseAsientoResponse> {
        val created = claseAsientoService.create(request.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(created.toResponse())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: ClaseAsientoRequest
    ): ClaseAsientoResponse {
        val updated = claseAsientoService.update(id, request.toEntity())
        return updated.toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        claseAsientoService.delete(id)
    }
}

data class ClaseAsientoRequest(
    @field:NotBlank val nombreClase: String,
    @field:NotBlank val descripcion: String
)

data class ClaseAsientoResponse(
    val id: Long?,
    val nombreClase: String?,
    val descripcion: String?
)

private fun ClaseAsiento.toResponse() =
    ClaseAsientoResponse(
        id = this.id,
        nombreClase = this.nombreClase,
        descripcion = this.descripcion
    )

private fun ClaseAsientoRequest.toEntity() =
    ClaseAsiento(
        nombreClase = this.nombreClase,
        descripcion = this.descripcion
    )

