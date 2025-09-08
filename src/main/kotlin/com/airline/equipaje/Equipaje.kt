package com.airline.equipaje

import com.airline.checkin.Checkin
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "equipaje")
data class Equipaje(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_checkin")
    var checkin: Checkin,

    @Column(name = "descripcion")
    var descripcion: String? = null,

    @Column(name = "peso")
    var peso: BigDecimal? = null
)
