package com.airline.pasajero

import com.airline.vuelo.Vuelo
import jakarta.persistence.*

@Entity
@Table(name = "pasajero")
data class Pasajero(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pasajero")
    var idPasajero: Long? = null,

    @Column(name = "nombre")
    var nombre: String,

    @Column(name = "apellido")
    var apellido: String,

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    var vuelo: Vuelo
)
