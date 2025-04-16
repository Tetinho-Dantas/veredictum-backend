package com.veredictum.backendveredictum.controller

import com.veredictum.backendveredictum.services.AgendamentoService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/atendimentos")
class AgendamentoController
    (
    private val agendamentoService: AgendamentoService,
    )
{



}