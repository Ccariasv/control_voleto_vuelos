package com.airline.boleto

import org.springframework.data.jpa.repository.JpaRepository

interface BoletoRepository : JpaRepository<Boleto, Long> {
    fun existsByVueloIdAndAsientoId(vueloId: Long?, asientoId: Long?): Boolean
}
