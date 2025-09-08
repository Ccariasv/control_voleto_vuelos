package com.airline.aeropuerto

import com.airline.ruta.Ruta
import jakarta.persistence.*

@Entity
@Table(name = "aeropuertos")
data class Aeropuerto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "nombre")
    var nombre: String? = null,

    @Column(name = "ciudad")
    var ciudad: String? = null,

    @Column(name = "pais")
    var pais: String? = null,

    @Column(name = "codigo_iata")
    var codigoIata: String,

    @OneToMany(mappedBy = "origen")
    var rutasOrigen: MutableList<Ruta> = mutableListOf(),

    @OneToMany(mappedBy = "destino")
    var rutasDestino: MutableList<Ruta> = mutableListOf()
)
