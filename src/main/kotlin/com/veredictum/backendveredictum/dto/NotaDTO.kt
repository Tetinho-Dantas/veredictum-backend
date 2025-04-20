package com.veredictum.backendveredictum.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class NotaDTO(
    val idNota: Int, // Nome do ID primário atualizado
    val dataCriacao: LocalDateTime, // Nome da coluna atualizado
    val etiqueta: String,
    val valor: Double,
    val dataVencimento: LocalDate?, // Nome da coluna atualizado
    val descricao: String?, // Nome da coluna atualizado
    val isPago: Boolean // Nome da coluna atualizado
)
