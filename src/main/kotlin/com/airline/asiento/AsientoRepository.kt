package com.airline.asiento

import org.springframework.data.jpa.repository.JpaRepository

interface AsientoRepository : JpaRepository<Asiento, Long>
