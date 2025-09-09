package com.airline.pago

import com.airline.boleto.BoletoService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("/pagos")
class PagoController(
    private val pagoService: PagoService,
    private val boletoService: BoletoService
) {
    @GetMapping
    fun getAll(): List<PagoDTO> = pagoService.findAll().map { it.toDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PagoDTO = pagoService.findById(id).toDTO()

    @PostMapping
    fun create(@Valid @RequestBody dto: PagoDTO): ResponseEntity<PagoDTO> {
        val boleto = boletoService.findEntityById(dto.boletoId!!)
        val created = pagoService.create(dto.toEntity(boleto))
        return ResponseEntity.status(HttpStatus.CREATED).body(created.toDTO())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: PagoDTO): PagoDTO {
        val boleto = boletoService.findEntityById(dto.boletoId!!)
        val updated = pagoService.update(id, dto.toEntity(boleto))
        return updated.toDTO()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        pagoService.delete(id)
        return ResponseEntity.noContent().build()
    }
}

data class PagoDTO(
    val id: Long? = null,
    @field:NotNull val boletoId: Long?,
    @field:NotBlank val metodoPago: String?,
    @field:NotNull val monto: BigDecimal?,
    @field:NotNull val fechaPago: LocalDate?
)

fun Pago.toDTO(): PagoDTO = PagoDTO(
    id = id,
    boletoId = boleto.id,
    metodoPago = metodoPago,
    monto = monto,
    fechaPago = fechaPago
)

fun PagoDTO.toEntity(boleto: com.airline.boleto.Boleto): Pago = Pago(
    id = id,
    boleto = boleto,
    metodoPago = metodoPago,
    monto = monto,
    fechaPago = fechaPago
)

