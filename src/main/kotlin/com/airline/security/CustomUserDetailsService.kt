package com.airline.security

import com.airline.usuario.UsuarioRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val usuarioRepository: UsuarioRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val usuario = usuarioRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("Usuario $username no encontrado")
        val authorities = usuario.rol?.let { listOf(SimpleGrantedAuthority("ROLE_$it")) } ?: emptyList()
        return User(usuario.username, usuario.passwordHash, authorities)
    }
}
