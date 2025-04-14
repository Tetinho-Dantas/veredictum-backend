package com.veredictum.backendveredictum.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.veredictum.backendveredictum.enums.StatusAgendamento
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
data class HistoricoAgendamento(

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idHistoricoAgendamento: Int? = null,

    @ManyToOne
    @JoinColumn(name = "fk_agendamento")
    @field:NotNull(message = "O agendamento é obrigatório")
    var agendamento: Agendamento,

    @Enumerated(EnumType.STRING)
    @Column(name = "status_agendamento")
    @field:NotNull(message = "O status do agendamento é obrigatório")
    var statusAgendamento: StatusAgendamento,

    @CreationTimestamp
    @Column(name = "data_hora_alteracao", updatable = false)
    var dataHoraAlteracao: LocalDateTime? = null

) {
    constructor() : this(
        null,
        Agendamento(),
        StatusAgendamento.AGENDADO, // Atribuindo um valor default do ENUM
        LocalDateTime.now()
    )
}
