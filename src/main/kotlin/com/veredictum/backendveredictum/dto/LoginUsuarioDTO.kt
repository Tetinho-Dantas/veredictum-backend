package com.veredictum.backendveredictum.dto

data class LoginUsuarioDTO(

    val nome: String,
    val email: String,
    val isAtivo: Boolean,
    val isLogado: Boolean

)
