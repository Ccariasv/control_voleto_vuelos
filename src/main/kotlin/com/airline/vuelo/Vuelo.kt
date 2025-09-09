package com.airline.vuelo

import com.airline.avion.Avion
import com.airline.boleto.Boleto
import com.airline.ruta.Ruta
import com.airline.turno.TurnoEmpleado
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "vuelos")
data class Vuelo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_ruta")
    var ruta: Ruta,

    @ManyToOne
    @JoinColumn(name = "id_avion")
    var avion: Avion,

    @Column(name = "salida_ts")
    var salidaTs: LocalDateTime? = null,

    @Column(name = "llegada_ts")
    var llegadaTs: LocalDateTime? = null,

    @Column(name = "estado")
    var estado: String? = null,

    @OneToMany(mappedBy = "vuelo")
    var boletos: MutableList<Boleto> = mutableListOf(),

    @OneToMany(mappedBy = "vuelo")
    var turnos: MutableList<TurnoEmpleado> = mutableListOf()
)
