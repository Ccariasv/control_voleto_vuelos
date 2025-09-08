package com.airline.pasajero

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PasajeroRepository : JpaRepository<Pasajero, Long>
