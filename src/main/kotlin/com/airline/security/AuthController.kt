package com.airline.security

import com.airline.usuario.Usuario
import com.airline.usuario.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val usuarioService: UsuarioService,
) {
    @PostMapping("/login")
    fun login(@RequestBody request: AuthRequest): ResponseEntity<AuthResponse> {
        val authToken = UsernamePasswordAuthenticationToken(request.username, request.password)
        authenticationManager.authenticate(authToken)
        val user = usuarioService.findByUsername(request.username)
        val token = jwtService.generateToken(user.username, user.rol)
        return ResponseEntity.ok(AuthResponse(token))
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<AuthResponse> {
        val user = Usuario(
            username = request.username,
            passwordHash = request.password,
            rol = request.rol,
            empleado = null
        )
        val saved = usuarioService.create(user)
        val token = jwtService.generateToken(saved.username, saved.rol)
        return ResponseEntity.ok(AuthResponse(token))
    }
}

data class AuthRequest(val username: String, val password: String)

data class AuthResponse(val token: String)

data class RegisterRequest(val username: String, val password: String, val rol: String)
