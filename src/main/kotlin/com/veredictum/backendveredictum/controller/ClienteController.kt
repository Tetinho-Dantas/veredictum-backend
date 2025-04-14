package com.veredictum.backendveredictum.controller

import com.veredictum.backendveredictum.repository.ClienteRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clientes")
class ClienteController (
    val repository: ClienteRepository
) {

    

}