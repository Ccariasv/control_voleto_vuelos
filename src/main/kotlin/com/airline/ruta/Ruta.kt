package com.airline.ruta

import com.airline.aeropuerto.Aeropuerto
import com.airline.vuelo.Vuelo
import jakarta.persistence.*

@Entity
@Table(name = "rutas")
data class Ruta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "a_origen_id")
    var origen: Aeropuerto,

    @ManyToOne
    @JoinColumn(name = "a_destino_id")
    var destino: Aeropuerto,

    @Column(name = "duracion_aprox_min")
    var duracionAproxMin: Double? = null,

    @Column(name = "distancia_km")
    var distanciaKm: Double? = null,

    @OneToMany(mappedBy = "ruta")
    var vuelos: MutableList<Vuelo> = mutableListOf()
)
