package com.veredictum.backendveredictum.controller

import com.veredictum.backendveredictum.dto.ClienteDTO
import com.veredictum.backendveredictum.dto.UsuarioDTO
import com.veredictum.backendveredictum.repository.UsuarioRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Usuários", description = "Endpoints para gerenciar usuários")
@RestController
@RequestMapping("/usuarios")
class UsuarioController (
    val repository: UsuarioRepository
) {

    @Operation(
        summary = "Buscar todos os usuários",
        description = "Retorna todos os usuários ativos ordenado por isAtivo. NotFound se nenhum usuário for encontrado."
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso"),
            ApiResponse(responseCode = "204", description = "Usuários cliente encontrado")
        ]
    )
    @GetMapping
    fun buscarTodos(): ResponseEntity<List<UsuarioDTO>> {
        val usuarios = repository.findAllOrderByIsAtivo()
        return if (usuarios.isEmpty()) {
            ResponseEntity.noContent().build() // 204 No Content
        } else {
            // Converte a lista de usuarios para DTOs
            ResponseEntity.ok(usuarios.map { it.toDTO() }) // 200 OK com lista de clientes
        }
    }

    @Operation(
        summary = "Logar com um usuário",
        description = "Retorna os dado do usuário a logar no sistema"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Usuário encontrado e logado com sucesso"),
            ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            ApiResponse(responseCode = "400", description = "Email ou Senha incorretos")
        ]
    )
    // EM ANDAMENTO
    @GetMapping
    fun logar(): ResponseEntity<List<UsuarioDTO>> {
        val usuarios = repository.findAllOrderByIsAtivo()
        return if (usuarios.isEmpty()) {
            ResponseEntity.noContent().build() // 204 No Content
        } else {
            // Converte a lista de usuarios para DTOs
            ResponseEntity.ok(usuarios.map { it.toDTO() }) // 200 OK com lista de clientes
        }
    }

}