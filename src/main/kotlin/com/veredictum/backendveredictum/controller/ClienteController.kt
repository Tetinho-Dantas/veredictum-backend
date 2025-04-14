package com.veredictum.backendveredictum.controller

import com.veredictum.backendveredictum.entity.Cliente
import com.veredictum.backendveredictum.repository.ClienteRepository
import org.springframework.http.ResponseEntity
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/clientes")
class ClienteController (
    val repository: ClienteRepository
) {

    @GetMapping
    fun buscarTodos(): ResponseEntity<List<Cliente>> {
        // Busca pelos clientes cadastrados e ordena pela coluna isAtivo
        // DEVE LISTAR TRUE PRIMEIRO
        val clientes = repository.findAllOrderByIsAtivo()

        // Se a lista de clientes estiver vazia, retorna 204 No Content
        return if (clientes.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            // Caso contrário, retorna 200 OK com a lista de clientes
            ResponseEntity.ok(clientes)
        }

    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<Cliente> {
        return repository.findById(id)
            // Se o cliente for encontrado (ou seja, o Optional contém um valor),
            // o método map será executado, retornando um ResponseEntity.ok(cliente)
            .map { cliente -> ResponseEntity.ok(cliente) }

            // Caso o Optional esteja vazio (cliente não encontrado),
            // será executado o orElse, que retorna ResponseEntity.notFound()
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun cadastrar(@RequestBody @Valid novoCliente: Cliente): ResponseEntity<Cliente> {
        // Salva o cliente e já pega a entidade gerada (com ID e outros atributos preenchidos pelo JPA)
        val clienteSalvo = repository.save(novoCliente)

        // Retorna 201 Created com o cliente no corpo da resposta
        return ResponseEntity.status(201).body(clienteSalvo)
    }


    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody @Valid clienteAtualizado: Cliente): ResponseEntity<Cliente> {

        // Se o corpo trouxer um ID diferente do da URL, retornamos 400 (Bad Request)
        if (clienteAtualizado.idCliente != null && clienteAtualizado.idCliente != id) {
            return ResponseEntity.badRequest().build()
        }

        return repository.findById(id).map { clienteExistente ->

            // Garante que o cliente atualizado use o ID correto da URL
            clienteAtualizado.idCliente = id

            // Salva e retorna o cliente atualizado
            val clienteSalvo = repository.save(clienteAtualizado)
            ResponseEntity.ok(clienteSalvo)

        }.orElseGet {
            // Retorna 404 se o cliente não for encontrado
            ResponseEntity.notFound().build()
        }

    }

    @PatchMapping("/{id}")
    fun atualizarParcial(
        @PathVariable id: Int,
        @RequestBody atualizacoes: Map<String, Any>
    ): ResponseEntity<Cliente> {

        return repository.findById(id).map { cliente ->

            atualizacoes.forEach { (chave, valor) ->
                val valorStr = valor.toString()

                // Ignora campos desconhecidos ou com valor vazio
                if (valorStr.isNotBlank()) {
                    when (chave) {
                        "nome" -> cliente.nome = valorStr
                        "email" -> cliente.email = valorStr
                        "rg" -> cliente.rg = valorStr
                        "cpf" -> cliente.cpf = valorStr
                        "cnpj" -> cliente.cnpj = valorStr
                        "dataNascimento" -> cliente.dataNascimento = LocalDate.parse(valorStr)
                        "dataInicio" -> cliente.dataInicio = LocalDate.parse(valorStr)
                        "endereco" -> cliente.endereco = valorStr
                        "cep" -> cliente.cep = valorStr
                        "descricao" -> cliente.descricao = valorStr
                        "inscricaoEstadual" -> cliente.inscricaoEstadual = valorStr
                        "proBono" -> cliente.isProBono = valorStr.toBoolean()
                        "ativo" -> cliente.isAtivo = valorStr.toBoolean()
                        "juridico" -> cliente.isJuridico = valorStr.toBoolean()
                        // Ignora campos desconhecidos
                        else -> {}
                    }
                }
            }

            val clienteAtualizado = repository.save(cliente)
            ResponseEntity.ok(clienteAtualizado)

        }.orElseGet {
            // Retorna 404 se o cliente não existir
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{id}/inativar")
    fun inativar(@PathVariable id: Int): ResponseEntity<Void> {
        val clienteOptional = repository.findById(id)

        return if (clienteOptional.isPresent) {
            val cliente = clienteOptional.get()
            cliente.isAtivo = false
            repository.save(cliente)
            ResponseEntity.noContent().build() // Retorna 204 sem corpo
        } else {
            ResponseEntity.status(404).build() // Retorna 404 se o cliente não for encontrado
        }
    }

    @PatchMapping("/{id}/ativar")
    fun ativar(@PathVariable id: Int): ResponseEntity<Void> {
        val clienteOptional = repository.findById(id)

        return if (clienteOptional.isPresent) {
            val cliente = clienteOptional.get()
            cliente.isAtivo = true
            repository.save(cliente)
            ResponseEntity.noContent().build() // Retorna 204 sem corpo
        } else {
            ResponseEntity.status(404).build() // Retorna 404 se o cliente não for encontrado
        }
    }

}