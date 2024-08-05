package br.com.fiap.lanchonete.cliente.client

import br.com.fiap.lanchonete.cliente.application.dto.request.ClienteRequest
import br.com.fiap.lanchonete.cliente.application.dto.response.ClienteResponse
import jakarta.validation.Valid
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "ClienteCucumber",
    url = "\${server.host}:\${server.port}\${server.servlet.context-path}/clientes"
)
@Service
interface ClienteCucumberClient {

    @PostMapping
    fun create(@Valid @RequestBody cliente: ClienteRequest
    ): ResponseEntity<ClienteResponse>


    @GetMapping("/{cpf}")
    fun findByCpf(@PathVariable(required = true) cpf: String): ResponseEntity<ClienteResponse>


    @DeleteMapping("/{cpf}")
    fun deleteUserByCpf(@PathVariable(required = true) cpf: String): ResponseEntity<ClienteResponse>
}