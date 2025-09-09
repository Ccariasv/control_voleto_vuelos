package com.airline.asiento

import com.airline.avion.Avion
import com.airline.boleto.Boleto
import com.airline.claseasiento.ClaseAsiento
import jakarta.persistence.*

@Entity
@Table(name = "asientos")
data class Asiento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_avion")
    var avion: Avion,

    @Column(name = "numero_asiento")
    var numeroAsiento: String,

    @ManyToOne
    @JoinColumn(name = "id_clase")
    var clase: ClaseAsiento? = null,

    @OneToMany(mappedBy = "asiento")
    var boletos: MutableList<Boleto> = mutableListOf()
)
