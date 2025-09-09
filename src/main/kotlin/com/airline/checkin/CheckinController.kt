package com.airline.checkin

import com.airline.boleto.Boleto
import com.airline.boleto.BoletoService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/checkins")
class CheckinController(
    private val checkinService: CheckinService,
    private val boletoService: BoletoService
) {

    @GetMapping
    fun getAll(): List<CheckinDTO> = checkinService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): CheckinDTO = checkinService.findById(id).toDTO()

    @PostMapping
    fun create(@Valid @RequestBody dto: CheckinDTO): ResponseEntity<CheckinDTO> {
        val boleto = boletoService.findById(dto.boletoId)
        val created = checkinService.create(dto.toEntity(boleto))
        return ResponseEntity.status(HttpStatus.CREATED).body(created.toDTO())
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody dto: CheckinDTO
    ): ResponseEntity<CheckinDTO> {
        val boleto = boletoService.findById(dto.boletoId)
        val updated = checkinService.update(id, dto.toEntity(boleto))
        return ResponseEntity.ok(updated.toDTO())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        checkinService.delete(id)
        return ResponseEntity.noContent().build()
    }
}

data class CheckinDTO(
    val id: Long? = null,
    @field:NotNull val boletoId: Long,
    val fechaCheckin: LocalDate? = null,
    val equipajeSiNo: Char? = null,
    val pesoEquipaje: BigDecimal? = null
)

fun Checkin.toDTO() = CheckinDTO(
    id = id,
    boletoId = boleto.id!!,
    fechaCheckin = fechaCheckin,
    equipajeSiNo = equipajeSiNo,
    pesoEquipaje = pesoEquipaje
)

fun CheckinDTO.toEntity(boleto: Boleto) = Checkin(
    id = id,
    boleto = boleto,
    fechaCheckin = fechaCheckin,
    equipajeSiNo = equipajeSiNo,
    pesoEquipaje = pesoEquipaje
)

