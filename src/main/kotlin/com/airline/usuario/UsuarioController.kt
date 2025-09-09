package com.airline.usuario

import com.airline.empleado.Empleado
import com.airline.empleado.EmpleadoService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
@Validated
class UsuarioController(
    private val usuarioService: UsuarioService,
    private val empleadoService: EmpleadoService,
) {
    @GetMapping
    fun getAll(): List<UsuarioResponse> =
        usuarioService.findAll().map { it.toResponse() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): UsuarioResponse =
        usuarioService.findById(id).toResponse()

    @PostMapping
    fun create(@Valid @RequestBody request: UsuarioRequest): ResponseEntity<UsuarioResponse> {
        val empleado = request.empleadoId?.let { empleadoService.findById(it) }
        val saved = usuarioService.create(request.toEntity(empleado))
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.toResponse())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: UsuarioRequest,
    ): UsuarioResponse {
        val empleado = request.empleadoId?.let { empleadoService.findById(it) }
        val updated = usuarioService.update(id, request.toEntity(empleado))
        return updated.toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        usuarioService.delete(id)
    }
}

data class UsuarioRequest(
    @field:NotBlank
    val username: String,

    @field:NotBlank
    @field:Size(min = 8)
    val password: String,

    @field:NotBlank
    val rol: String,

    val empleadoId: Long?,
)

data class UsuarioResponse(
    val id: Long?,
    val username: String,
    val rol: String?,
    val empleadoId: Long?,
)

private fun UsuarioRequest.toEntity(empleado: Empleado?) = Usuario(
    username = this.username,
    passwordHash = this.password,
    rol = this.rol,
    empleado = empleado,
)

private fun Usuario.toResponse() = UsuarioResponse(
    id = this.id,
    username = this.username,
    rol = this.rol,
    empleadoId = this.empleado?.id,
)
