package com.veredictum.backendveredictum.enums

enum class StatusAgendamento(val descricao: String) {
    AGENDADO("Agendado"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado"),
    ATRASADO("Atrasado");

    companion object {
        fun fromString(value: String): StatusAgendamento? {
            return entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}
