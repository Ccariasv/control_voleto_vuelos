package com.airline.checkin

import com.airline.boleto.Boleto
import com.airline.equipaje.Equipaje
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "checkin")
data class Checkin(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @OneToOne
    @JoinColumn(name = "id_boleto")
    var boleto: Boleto,

    @Column(name = "fecha_checkin")
    var fechaCheckin: LocalDate? = null,

    @Column(name = "equipaje_si_no")
    var equipajeSiNo: Char? = null,

    @Column(name = "peso_equipaje")
    var pesoEquipaje: BigDecimal? = null,

    @OneToMany(mappedBy = "checkin")
    var equipajes: MutableList<Equipaje> = mutableListOf()
)
