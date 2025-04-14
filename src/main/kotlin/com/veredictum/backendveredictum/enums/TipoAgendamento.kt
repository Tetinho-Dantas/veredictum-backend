package com.veredictum.backendveredictum.enums

enum class TipoAgendamento(val descricao: String) {

    ATENDIMENTO("Atendimento"),
    NOTA_FISCAL("Nota Fiscal"),
    CONTA("Conta");

    companion object {
        fun fromString(value: String): TipoAgendamento? {
            return entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}
