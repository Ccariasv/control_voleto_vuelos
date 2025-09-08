package com.airline.ruta

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RutaRepository : JpaRepository<Ruta, Long>
