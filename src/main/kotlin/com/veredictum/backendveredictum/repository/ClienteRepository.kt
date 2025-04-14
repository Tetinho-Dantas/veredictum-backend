package com.veredictum.backendveredictum.repository

import com.veredictum.backendveredictum.entity.Cliente
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, Int> {

    fun findByNomeContainingIgnoreCaseOrderByNomeAsc(nome: String): List<Cliente>

}