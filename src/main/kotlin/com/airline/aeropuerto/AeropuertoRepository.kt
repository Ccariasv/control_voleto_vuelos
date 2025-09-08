package com.airline.aeropuerto

import org.springframework.data.jpa.repository.JpaRepository

interface AeropuertoRepository : JpaRepository<Aeropuerto, Long> {
    fun findByCodigoIata(codigoIata: String): Aeropuerto?
}
