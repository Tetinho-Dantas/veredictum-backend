package com.veredictum.backendveredictum.repository

import com.veredictum.backendveredictum.entity.HistoricoAgendamento
import org.springframework.data.jpa.repository.JpaRepository

interface HistoricoAgendamentoRepository: JpaRepository<HistoricoAgendamento, Int> {

}