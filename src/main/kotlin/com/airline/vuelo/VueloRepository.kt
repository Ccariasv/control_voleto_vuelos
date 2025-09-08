package com.airline.vuelo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VueloRepository : JpaRepository<Vuelo, Long>
