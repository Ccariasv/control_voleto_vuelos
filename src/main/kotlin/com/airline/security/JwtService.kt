package com.airline.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor("super-secret-key-which-is-long-enough-123456".toByteArray())
    private val expirationMillis: Long = 1000 * 60 * 60 // 1 hour

    fun generateToken(username: String, role: String?): String {
        val claims = mutableMapOf<String, Any>()
        role?.let { claims["role"] = it }
        val now = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + expirationMillis))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun extractUsername(token: String): String? = try {
        Jwts.parser().verifyWith(secretKey).build()
            .parseSignedClaims(token).payload.subject
    } catch (ex: Exception) {
        null
    }

    fun isTokenValid(token: String, username: String): Boolean {
        val extracted = extractUsername(token)
        return extracted == username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean = try {
        val claims = Jwts.parser().verifyWith(secretKey).build()
            .parseSignedClaims(token).payload
        claims.expiration.before(Date())
    } catch (ex: Exception) {
        true
    }
}

