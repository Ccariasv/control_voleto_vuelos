package com.airline.equipaje

import com.airline.checkin.CheckinService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/equipajes")
class EquipajeController(
    private val equipajeService: EquipajeService,
    private val checkinService: CheckinService
) {
    @GetMapping
    fun getAll(): ResponseEntity<List<EquipajeDTO>> =
        ResponseEntity.ok(equipajeService.findAll().map { it.toDTO() })

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<EquipajeDTO> =
        ResponseEntity.ok(equipajeService.findById(id).toDTO())

    @PostMapping
    fun create(@Valid @RequestBody dto: EquipajeDTO): ResponseEntity<EquipajeDTO> {
        val checkin = checkinService.findById(dto.idCheckin!!)
        val saved = equipajeService.create(dto.toEntity(checkin))
        return ResponseEntity.status(HttpStatus.CREATED).body(saved.toDTO())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: EquipajeDTO
    ): ResponseEntity<EquipajeDTO> {
        val checkin = checkinService.findById(dto.idCheckin!!)
        val updated = equipajeService.update(id, dto.toEntity(checkin))
        return ResponseEntity.ok(updated.toDTO())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        equipajeService.delete(id)
        return ResponseEntity.noContent().build()
    }
}

