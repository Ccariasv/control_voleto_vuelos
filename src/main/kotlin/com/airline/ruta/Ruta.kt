package com.airline.ruta

import com.airline.vuelo.Vuelo
import jakarta.persistence.*

@Entity
@Table(name = "ruta")
data class Ruta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    var idRuta: Long? = null,

    @Column(name = "origen")
    var origen: String,

    @Column(name = "destino")
    var destino: String,

    @OneToMany(mappedBy = "ruta")
    var vuelos: MutableList<Vuelo> = mutableListOf()
)
