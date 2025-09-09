package com.airline.turno

import com.airline.empleado.Empleado
import com.airline.empleado.EmpleadoService
import com.airline.exception.NotFoundException
import com.airline.vuelo.Vuelo
import com.airline.vuelo.VueloService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/turnos-empleado")
class TurnoEmpleadoController(
    private val turnoEmpleadoService: TurnoEmpleadoService,
    private val empleadoService: EmpleadoService,
    private val vueloService: VueloService
) {
    @GetMapping
    fun getAll(): ResponseEntity<List<TurnoEmpleadoDto>> =
        ResponseEntity.ok(turnoEmpleadoService.findAll().map { it.toDto() })

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TurnoEmpleadoDto> = try {
        ResponseEntity.ok(turnoEmpleadoService.findById(id).toDto())
    } catch (e: NotFoundException) {
        ResponseEntity.notFound().build()
    }

    @PostMapping
    fun create(@Valid @RequestBody dto: TurnoEmpleadoDto): ResponseEntity<TurnoEmpleadoDto> {
        val empleado = empleadoService.findById(dto.empleadoId)
        val vuelo = vueloService.findEntityById(dto.vueloId)
        val saved = turnoEmpleadoService.create(dto.toEntity(empleado, vuelo))
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.toDto())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: TurnoEmpleadoDto
    ): ResponseEntity<TurnoEmpleadoDto> = try {
        val empleado = empleadoService.findById(dto.empleadoId)
        val vuelo = vueloService.findEntityById(dto.vueloId)
        val saved = turnoEmpleadoService.update(id, dto.toEntity(empleado, vuelo))
        ResponseEntity.ok(saved.toDto())
    } catch (e: NotFoundException) {
        ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> = try {
        turnoEmpleadoService.delete(id)
        ResponseEntity.noContent().build()
    } catch (e: NotFoundException) {
        ResponseEntity.notFound().build()
    }
}

data class TurnoEmpleadoDto(
    val id: Long? = null,
    @field:NotNull val empleadoId: Long,
    @field:NotNull val vueloId: Long,
    @field:NotBlank val rolEnVuelo: String
)

fun TurnoEmpleado.toDto(): TurnoEmpleadoDto =
    TurnoEmpleadoDto(
        id = this.id,
        empleadoId = this.empleado.id!!,
        vueloId = this.vuelo.id!!,
        rolEnVuelo = this.rolEnVuelo ?: ""
    )

fun TurnoEmpleadoDto.toEntity(empleado: Empleado, vuelo: Vuelo): TurnoEmpleado =
    TurnoEmpleado(id = this.id, empleado = empleado, vuelo = vuelo, rolEnVuelo = this.rolEnVuelo)

