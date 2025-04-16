package com.veredictum.backendveredictum.repository

import com.veredictum.backendveredictum.entity.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UsuarioRepository: JpaRepository<Usuario, Int> {

    @Query("SELECT u FROM Usuario u ORDER BY u.isAtivo DESC")
    fun findAllOrderByIsAtivo() : List<Usuario>

}