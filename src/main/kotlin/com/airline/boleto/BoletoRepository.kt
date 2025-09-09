package com.airline.boleto

import org.springframework.data.jpa.repository.JpaRepository

interface BoletoRepository : JpaRepository<Boleto, Long>
