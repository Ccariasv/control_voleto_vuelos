package com.airline.empleado

import org.springframework.data.jpa.repository.JpaRepository

interface EmpleadoRepository : JpaRepository<Empleado, Long>
