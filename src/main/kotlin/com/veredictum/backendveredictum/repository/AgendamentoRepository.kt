package com.veredictum.backendveredictum.repository

import com.veredictum.backendveredictum.entity.Agendamento
import org.springframework.data.jpa.repository.JpaRepository

interface AgendamentoRepository: JpaRepository<Agendamento, Int> {

}