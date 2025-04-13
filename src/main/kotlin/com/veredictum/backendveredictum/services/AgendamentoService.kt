package com.veredictum.backendveredictum.services

import com.veredictum.backendveredictum.repository.*

class AgendamentoService (
    val agendamentoRepository: AgendamentoRepository,
    val clienteRepository: ClienteRepository,
    val usuarioRepository: UsuarioRepository,
    val historicoAgendamentoRepository: HistoricoAgendamentoRepository,
    val statusAgendamentoRepository: StatusAgendamentoRepository,
) {



}