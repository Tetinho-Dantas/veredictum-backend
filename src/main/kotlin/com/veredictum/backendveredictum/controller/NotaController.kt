package com.veredictum.backendveredictum.controller

import com.veredictum.backendveredictum.entity.Nota
import com.veredictum.backendveredictum.dto.NotaDTO
import com.veredictum.backendveredictum.repository.NotaRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Notas", description = "Endpoints para gerenciar notas")
@RestController
@RequestMapping("/notas")
class NotaController(
    val repository: NotaRepository
) {

    @Operation(
        summary = "Buscar todas as notas",
        description = "Retorna todas as notas."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Notas retornadas com sucesso"),
            ApiResponse(responseCode = "204", description = "Nenhuma nota encontrada")
        ]
    )
    @GetMapping
    fun buscarTodas(): ResponseEntity<List<NotaDTO>> {
        val notas = repository.findAll()
        return if (notas.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(notas.map { it.toDTO() })
        }
    }

    @Operation(
        summary = "Buscar nota por ID",
        description = "Retorna a nota com o ID fornecido."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Nota encontrada"),
            ApiResponse(responseCode = "404", description = "Nota não encontrada")
        ]
    )
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<NotaDTO> {
        return repository.findById(id)
            .map { nota -> ResponseEntity.ok(nota.toDTO()) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(
        summary = "Cadastrar nova nota",
        description = "Cria uma nova nota e retorna a nota cadastrada."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Nota criada com sucesso"),
            ApiResponse(responseCode = "400", description = "Dados inválidos"),
            ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        ]
    )
    @PostMapping
    fun cadastrar(@RequestBody @Valid novaNotaDTO: NotaDTO): ResponseEntity<NotaDTO> {
        val novaNota = Nota(
            dataCriacao = novaNotaDTO.dataCriacao,
            etiqueta = novaNotaDTO.etiqueta,
            valor = novaNotaDTO.valor,
            dataVencimento = novaNotaDTO.dataVencimento,
            descricao = novaNotaDTO.descricao,
            isPago = novaNotaDTO.isPago
        )
        val notaSalva = repository.save(novaNota)
        return ResponseEntity.status(HttpStatus.CREATED).body(notaSalva.toDTO())
    }

    @Operation(
        summary = "Atualizar nota",
        description = "Atualiza os dados da nota com o ID informado. Se o corpo contiver um ID diferente, é ignorado."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Nota atualizada com sucesso"),
            ApiResponse(responseCode = "400", description = "ID inconsistente entre a URL e o corpo"),
            ApiResponse(responseCode = "404", description = "Nota não encontrada")
        ]
    )
    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody @Valid notaAtualizada: NotaDTO): ResponseEntity<NotaDTO> {
        return repository.findById(id).map { notaExistente ->
            val nota = Nota(
                idNota = id, // Atualizado para idNota
                dataCriacao = notaAtualizada.dataCriacao, // Atualizado
                etiqueta = notaAtualizada.etiqueta,
                valor = notaAtualizada.valor,
                dataVencimento = notaAtualizada.dataVencimento, // Atualizado
                descricao = notaAtualizada.descricao, // Atualizado
                isPago = notaAtualizada.isPago // Atualizado
            )
            val notaSalva = repository.save(nota)
            ResponseEntity.ok(notaSalva.toDTO())
        }.orElseGet {
            ResponseEntity.notFound().build()
        }
    }

    @Operation(
        summary = "Excluir nota",
        description = "Exclui a nota com o ID fornecido."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Nota excluída com sucesso"),
            ApiResponse(responseCode = "404", description = "Nota não encontrada")
        ]
    )
    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id: Int): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}