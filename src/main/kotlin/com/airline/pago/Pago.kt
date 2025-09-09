package com.airline.pago

import com.airline.boleto.Boleto
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "pagos")
data class Pago(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_boleto")
    var boleto: Boleto,

    @Column(name = "metodo_pago")
    var metodoPago: String? = null,

    @Column(name = "monto")
    var monto: BigDecimal? = null,

    @Column(name = "fecha_pago")
    var fechaPago: LocalDate? = null
)
