package com.airline.empleado

import com.airline.turno.TurnoEmpleado
import com.airline.usuario.Usuario
import jakarta.persistence.*

@Entity
@Table(name = "empleados")
data class Empleado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "nombres")
    var nombres: String? = null,

    @Column(name = "apellidos")
    var apellidos: String? = null,

    @Column(name = "puesto")
    var puesto: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "telefono")
    var telefono: String? = null,

    @OneToMany(mappedBy = "empleado")
    var turnos: MutableList<TurnoEmpleado> = mutableListOf(),

    @OneToMany(mappedBy = "empleado")
    var usuarios: MutableList<Usuario> = mutableListOf()
)
