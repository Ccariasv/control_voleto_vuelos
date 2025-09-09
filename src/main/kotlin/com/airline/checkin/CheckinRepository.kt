package com.airline.checkin

import org.springframework.data.jpa.repository.JpaRepository

interface CheckinRepository : JpaRepository<Checkin, Long>
