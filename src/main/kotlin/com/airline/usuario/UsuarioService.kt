package com.airline.usuario

import com.airline.exception.NotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun findAll(): List<Usuario> = usuarioRepository.findAll()

    fun findById(id: Long): Usuario =
        usuarioRepository.findById(id).orElseThrow { NotFoundException("Usuario $id no encontrado") }

    fun findByUsername(username: String): Usuario =
        usuarioRepository.findByUsername(username)
            ?: throw NotFoundException("Usuario $username no encontrado")

    fun create(usuario: Usuario): Usuario {
        usuario.passwordHash = passwordEncoder.encode(usuario.passwordHash)
        return usuarioRepository.save(usuario)
    }

    fun update(id: Long, usuario: Usuario): Usuario {
        if (!usuarioRepository.existsById(id)) {
            throw NotFoundException("Usuario $id no encontrado")
        }
        usuario.id = id
        usuario.passwordHash = passwordEncoder.encode(usuario.passwordHash)
        return usuarioRepository.save(usuario)
    }

    fun delete(id: Long) {
        if (!usuarioRepository.existsById(id)) {
            throw NotFoundException("Usuario $id no encontrado")
        }
        usuarioRepository.deleteById(id)
    }
}
