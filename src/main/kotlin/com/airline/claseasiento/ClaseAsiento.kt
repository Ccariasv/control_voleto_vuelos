package com.airline.claseasiento

import com.airline.asiento.Asiento
import jakarta.persistence.*

@Entity
@Table(name = "clases_asiento")
data class ClaseAsiento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "nombre_clase")
    var nombreClase: String? = null,

    @Column(name = "descripcion")
    var descripcion: String? = null,

    @OneToMany(mappedBy = "clase")
    var asientos: MutableList<Asiento> = mutableListOf()
)
