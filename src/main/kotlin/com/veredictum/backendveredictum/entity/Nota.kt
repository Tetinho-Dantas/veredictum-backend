package com.veredictum.backendveredictum.entity

import com.veredictum.backendveredictum.dto.NotaDTO
import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "nota")
data class Nota(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idNota: Int? = null,

    @NotNull(message = "A data de criação é obrigatória")
    var dataCriacao: LocalDateTime = LocalDateTime.now(),

    @field:Size(max = 30, message = "A etiqueta deve ter no máximo 30 caracteres")
    var etiqueta: String? = null,

    @field:NotNull(message = "O valor é obrigatório")
    var valor: Double? = null,

    var dataVencimento: LocalDate? = null,

    @Column(columnDefinition = "TEXT")
    var descricao: String? = null,

    var isPago: Boolean? = false
) {
    fun toDTO(): NotaDTO {
        return NotaDTO(
            idNota = this.idNota ?: 0,
            dataCriacao = this.dataCriacao,
            etiqueta = this.etiqueta ?: "",
            valor = this.valor ?: 0.0,
            dataVencimento = this.dataVencimento,
            descricao = this.descricao ?: "",
            isPago = this.isPago ?: false
        )
    }
}