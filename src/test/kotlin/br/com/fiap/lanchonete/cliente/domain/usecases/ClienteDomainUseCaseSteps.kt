package br.com.fiap.lanchonete.cliente.domain.usecases

import br.com.fiap.lanchonete.cliente.application.gateway.ClienteRepositoryGateway
import br.com.fiap.lanchonete.cliente.domain.entities.Cliente
import br.com.fiap.lanchonete.cliente.domain.exception.ClienteExceptionEnum
import br.com.fiap.lanchonete.exception.BusinessException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.mock.mockito.MockBean

@ExtendWith(MockitoExtension::class)
class ClienteDomainUseCaseSteps {

    @Mock
    lateinit var clienteRepositoryGateway: ClienteRepositoryGateway

    @InjectMocks
    lateinit var clienteDomainUseCase: ClienteDomainUseCase

    private lateinit var cliente: Cliente
    private lateinit var result: Cliente
    private lateinit var exception: BusinessException

    @Given("^cliente novo com cpf ([^\"]*), nome ([^\"]*) e email ([^\"]*)")
    fun givenCliente(cpf: String, nome: String, email: String) {
        cliente = Cliente(1L, cpf, nome, email)
        `when`(cliente.cpf?.let { clienteRepositoryGateway.existsByCpf(it) }).thenReturn(false)
        `when`(cliente.email?.let { clienteRepositoryGateway.existsByEmail(it) }).thenReturn(false)
        `when`(clienteRepositoryGateway.save(cliente)).thenReturn(cliente)
    }

    @When("criar o cliente novo")
    fun whenCrioClienteNovo(cpf: String) {
        result = clienteDomainUseCase.create(cliente)
    }

    @Then("o sistema cria o cliente novo")
    fun thenSistemaCriaClienteNovo(){

        assertEquals(cliente, result)
        verify(clienteRepositoryGateway).save(cliente)
    }

    @Given("^cliente com cpf em uso cpf ([^\"]*), nome ([^\"]*) e email ([^\"]*)")
    fun givenClienteCpfEmUso(cpf: String, nome: String, email: String) {
        cliente = Cliente(1L, cpf, nome, email)
        `when`(cliente.cpf?.let { clienteRepositoryGateway.existsByCpf(it) }).thenReturn(true)
    }


    @When("criar cliente com excecao de negocio")
    fun whenCriarClienteComCpfEmUso(cpf: String) {
        exception = assertThrows(BusinessException::class.java) {
            clienteDomainUseCase.create(cliente)
        }
    }

    @Then("o sistema deve lancar uma excecao de cpf existente")
    fun thenSistemaDeveJogarExcecaoCpfExistente(){

        assertEquals(ClienteExceptionEnum.CLIENTE_ALREADY_EXISTS_CPF, exception.exceptionEnum)
    }

    @Given("^cliente com email em uso cpf ([^\"]*), nome ([^\"]*) e email ([^\"]*)")
    fun givenClienteEmailEmUso(cpf: String, nome: String, email: String) {
        `when`(cliente.cpf?.let { clienteRepositoryGateway.existsByCpf(it) }).thenReturn(false)
        `when`(cliente.email?.let { clienteRepositoryGateway.existsByEmail(it) }).thenReturn(true)
    }

    @Then("o sistema deve lancar uma excecao de email existente")
    fun thenSistemaDeveJogarExcecaoEmailExistente(){

        assertEquals(ClienteExceptionEnum.CLIENTE_ALREADY_EXISTS_EMAIL, exception.exceptionEnum)
    }

    @Given("^busco cliente por cpf ([^\"]*)")
    fun givenBuscoClientePorCfp(cpf: String) {
        `when`(clienteRepositoryGateway.findByCpf(cpf)).thenReturn(cliente)
    }

    @When("^sistema procuro Cliente com cpf ([^\"]*)")
    fun whenSistemaBuscaClienteComCPF(cpf: String) {
        result = clienteDomainUseCase.findByCpf(cpf)
    }

    @Then("cliente deve ser encontrado")
    fun thenClienteDeveSerEncontrado(){

        assertEquals(cliente, result)
    }

    @Given("^cliente por cpf que nao existe")
    fun givenBuscoClienteQueNaoExiste() {
        val cpf = "987.654.321-09"
        `when`(clienteRepositoryGateway.findByCpf(cpf)).thenReturn(null)

    }

    @When("busca cliente com uma excecao de negocio")
    fun whenBuscoClienteComExcecao(cpf: String) {
        exception = assertThrows(BusinessException::class.java) {
            clienteDomainUseCase.findByCpf(cpf)
        }
    }

    @Then("o sistema deve lancar uma excecao de cliente nao encontrado")
    fun thenSistemaLancaExcecaoClienteNaoEncontrado(){

        assertEquals(ClienteExceptionEnum.CLIENTE_NOT_FOUND, exception.exceptionEnum)
    }

}