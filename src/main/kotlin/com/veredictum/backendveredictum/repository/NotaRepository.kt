package com.veredictum.backendveredictum.repository

import com.veredictum.backendveredictum.entity.Nota
import org.springframework.data.jpa.repository.JpaRepository

interface NotaRepository: JpaRepository<Nota, Int> {
}