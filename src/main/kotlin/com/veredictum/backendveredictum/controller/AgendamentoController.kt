package com.veredictum.backendveredictum.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/atendimentos")
class AgendamentoController
    (
    val agendamentoService: AgendamentoService,
    )
{



}