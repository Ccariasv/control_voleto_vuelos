package com.airline.pago

import org.springframework.data.jpa.repository.JpaRepository

interface PagoRepository : JpaRepository<Pago, Long>
