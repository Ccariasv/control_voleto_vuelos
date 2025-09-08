package com.airline.vuelo

import com.airline.avion.Avion
import com.airline.ruta.Ruta
import com.airline.pasajero.Pasajero
import jakarta.persistence.*

@Entity
@Table(name = "vuelo")
data class Vuelo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    var idVuelo: Long? = null,

    @Column(name = "numero_vuelo")
    var numeroVuelo: String,

    @ManyToOne
    @JoinColumn(name = "id_ruta")
    var ruta: Ruta,

    @ManyToOne
    @JoinColumn(name = "id_avion")
    var avion: Avion,

    @OneToMany(mappedBy = "vuelo")
    var pasajeros: MutableList<Pasajero> = mutableListOf()
)
