package br.com.fiap.lanchonete.cliente.domain.usecases

import br.com.fiap.lanchonete.cliente.application.gateway.ClienteRepositoryGateway
import br.com.fiap.lanchonete.cliente.domain.entities.Cliente
import br.com.fiap.lanchonete.cliente.domain.exception.ClienteExceptionEnum
import br.com.fiap.lanchonete.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ClienteDomainUseCase(private val clienteRepositoryGateway: ClienteRepositoryGateway) {

    @Transactional
    fun create(cliente: Cliente): Cliente {

        validate(cliente)

        return clienteRepositoryGateway.save(cliente)
    }

    fun findByCpf(cpf: String): Cliente{
        val cliente = clienteRepositoryGateway.findByCpf(cpf)
        if(cliente?.ativo == true){
            return cliente
        }
        throw BusinessException(ClienteExceptionEnum.CLIENTE_NOT_FOUND)
    }

    fun clienteExists(cliente: Cliente?) = cliente?.cpf?.let { clienteRepositoryGateway.existsByCpf(it) }

    fun emailExists(cliente: Cliente) = cliente.email?.let { clienteRepositoryGateway.existsByEmail(it) }

    fun validate(cliente: Cliente) {
        if(clienteExists(cliente) == true) {
            throw BusinessException(ClienteExceptionEnum.CLIENTE_ALREADY_EXISTS_CPF)
        }

        if(emailExists(cliente) == true){
            throw BusinessException(ClienteExceptionEnum.CLIENTE_ALREADY_EXISTS_EMAIL)
        }
    }

    @Transactional
    fun deleteUserByCpf(cliente: Cliente) {
        cliente.logicalRemove()
    }

}