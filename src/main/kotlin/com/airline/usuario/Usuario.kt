package com.airline.usuario

import com.airline.empleado.Empleado
import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "username")
    var username: String,

    @Column(name = "password_hash")
    var passwordHash: String,

    @Column(name = "rol")
    var rol: String? = null,

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    var empleado: Empleado? = null
)
