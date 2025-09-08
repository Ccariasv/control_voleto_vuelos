package com.airline.avion

import com.airline.vuelo.Vuelo
import jakarta.persistence.*

@Entity
@Table(name = "avion")
data class Avion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avion")
    var idAvion: Long? = null,

    @Column(name = "matricula")
    var matricula: String,

    @Column(name = "modelo")
    var modelo: String,

    @Column(name = "capacidad")
    var capacidad: Int,

    @OneToMany(mappedBy = "avion")
    var vuelos: MutableList<Vuelo> = mutableListOf()
)
