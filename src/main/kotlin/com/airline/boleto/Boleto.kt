package com.airline.boleto

import com.airline.asiento.Asiento
import com.airline.pasajero.Pasajero
import com.airline.pago.Pago
import com.airline.checkin.Checkin
import com.airline.vuelo.Vuelo
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "boletos")
data class Boleto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    var vuelo: Vuelo,

    @ManyToOne
    @JoinColumn(name = "id_pasajero")
    var pasajero: Pasajero,

    @ManyToOne
    @JoinColumn(name = "id_asiento")
    var asiento: Asiento,

    @Column(name = "precio")
    var precio: BigDecimal? = null,

    @Column(name = "fecha_emision")
    var fechaEmision: LocalDate? = null,

    @Column(name = "estado")
    var estado: String? = null,

    @OneToMany(mappedBy = "boleto")
    var pagos: MutableList<Pago> = mutableListOf(),

    @OneToOne(mappedBy = "boleto")
    var checkin: Checkin? = null
)
