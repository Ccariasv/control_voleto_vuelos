package com.airline.avion

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AvionRepository : JpaRepository<Avion, Long>
