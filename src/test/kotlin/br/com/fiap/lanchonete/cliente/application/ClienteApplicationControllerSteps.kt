//import br.com.fiap.lanchonete.cliente.application.controller.ClienteApplicationController
//import br.com.fiap.lanchonete.cliente.application.dto.request.ClienteRequest
//import br.com.fiap.lanchonete.cliente.application.dto.response.ClienteResponse
//import br.com.fiap.lanchonete.cliente.domain.entities.Cliente
//import br.com.fiap.lanchonete.cliente.domain.entities.extension.toEntity
//import br.com.fiap.lanchonete.cliente.domain.usecases.ClienteDomainUseCase
//import io.cucumber.java.ParameterType
//import io.cucumber.java.en.Given
//import io.cucumber.java.en.Then
//import io.cucumber.java.en.When
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.`when`
//
//class ClienteApplicationControllerSteps {
//
//    private lateinit var clienteDomainUseCase: ClienteDomainUseCase
//    private lateinit var clienteApplicationController: ClienteApplicationController
//    private lateinit var clienteRequestDto: ClienteRequest
//    private lateinit var clienteResponse: ClienteResponse
//    private lateinit var result: ClienteResponse
//
//    @ParameterType(".*")
//    fun string(parameter: String): String {
//        return parameter
//    }
//
//    @Given("^um cliente com CPF \"([^\"]*)\", email ([^\"]*) e nome([^\"]*)")
//    fun givenCliente(cpf: String, email: String, nome: String) {
//        clienteRequestDto = ClienteRequest(cpf, email, nome)
//        clienteResponse = ClienteResponse(cpf, email, nome)
//    }
//
//    @Given("^cliente com CPF \"([^\"]*)\", email ([^\"]*) e nome ([^\"]*) existente no sistema")
//    fun givenClienteExistenteNoSistema(cpf: String, email: String, nome: String) {
//        clienteRequestDto = ClienteRequest(cpf, email, nome)
//        clienteResponse = ClienteResponse(cpf, email, nome)
//    }
//
//    @When("^o cliente é criado$")
//    fun whenClienteCriado() {
//        clienteDomainUseCase = mock(ClienteDomainUseCase::class.java)
//        clienteApplicationController = ClienteApplicationController(clienteDomainUseCase)
//        val cliente = clienteRequestDto.toEntity()
//        `when`(clienteDomainUseCase.create(cliente)).thenReturn(cliente)
//        result = clienteApplicationController.create(clienteRequestDto)
//    }
//
//    @Then("^o sistema retorna os dados do cliente criado com CPF \"([^\"]*)\", email ([^\"]*) e nome ([^\"]*)")
//    fun thenSistemaRetornaDados(cpf: String, email: String, nome: String) {
//        val expectedResponse = ClienteResponse(cpf, email, nome)
//        assertEquals(expectedResponse, result)
//    }
//
//    @When("^eu busco pelo cliente com CPF \"([^\"]*)\"")
//    fun whenBuscaCliente(cpf: String) {
//        clienteDomainUseCase = mock(ClienteDomainUseCase::class.java)
//        clienteApplicationController = ClienteApplicationController(clienteDomainUseCase)
//        val cliente = Cliente(1, cpf, clienteRequestDto.email, clienteRequestDto.nome)
//        `when`(clienteDomainUseCase.findByCpf(cpf)).thenReturn(cliente)
//        result = clienteApplicationController.findByCpf(cpf)
//    }
//
//    @Then("^o sistema retorna os dados do cliente com CPF \"([^\"]*)\", email ([^\"]*) e nome ([^\"]*)")
//    fun thenSistemaRetornaDadosBusca(cpf: String, email: String, nome: String) {
//        val expectedResponse = ClienteResponse(cpf, email, nome)
//        assertEquals(expectedResponse, result)
//    }
//}