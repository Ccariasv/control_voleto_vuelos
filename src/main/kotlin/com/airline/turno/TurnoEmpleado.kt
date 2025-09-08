package com.airline.turno

import com.airline.empleado.Empleado
import com.airline.vuelo.Vuelo
import jakarta.persistence.*

@Entity
@Table(name = "turnos_empleado")
data class TurnoEmpleado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    var empleado: Empleado,

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    var vuelo: Vuelo,

    @Column(name = "rol_en_vuelo")
    var rolEnVuelo: String? = null
)
