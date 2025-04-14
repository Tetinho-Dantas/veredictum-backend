package com.veredictum.backendveredictum.repository

import com.veredictum.backendveredictum.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Int> {

}