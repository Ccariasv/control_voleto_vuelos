package com.airline.avion

import com.airline.asiento.Asiento
import com.airline.vuelo.Vuelo
import jakarta.persistence.*

@Entity
@Table(name = "aviones")
data class Avion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "modelo")
    var modelo: String? = null,

    @Column(name = "fabricante")
    var fabricante: String? = null,

    @Column(name = "capacidad_total")
    var capacidadTotal: Int? = null,

    @Column(name = "estado")
    var estado: String? = null,

    @OneToMany(mappedBy = "avion")
    var asientos: MutableList<Asiento> = mutableListOf(),

    @OneToMany(mappedBy = "avion")
    var vuelos: MutableList<Vuelo> = mutableListOf()
)
