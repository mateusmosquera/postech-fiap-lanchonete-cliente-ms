package br.com.fiap.lanchonete.cliente.infrastructure.web.controller

import br.com.fiap.lanchonete.cliente.application.dto.request.ClienteRequest
import br.com.fiap.lanchonete.cliente.application.dto.response.ClienteResponse
import br.com.fiap.lanchonete.cliente.client.ClienteCucumberClient
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.http.ResponseEntity

class ClienteHttpControllerSteps(var clienteCucumberClient: ClienteCucumberClient){

    private lateinit var clienteRequest: ClienteRequest
    private lateinit var clienteResponse: ResponseEntity<ClienteResponse>

    @Given("^que o cliente envia uma solicitação para criar um novo cliente")
    fun givenClienteRequestIsSentToCreateANewClient() {

        clienteRequest = ClienteRequest("686.524.400-18", "Cliente Teste1", "cliente1@teste.com")

        clienteResponse = clienteCucumberClient.create(clienteRequest)

    }

    @When("^cliente recebe a solicitacao com code status (\\d+)")
    fun whenClienteRecebeSolicitacao(status:Int) {
        assertEquals(clienteResponse.statusCode.value(), status)
    }


    @Then("o cliente recebe a resposta")
    fun thenClienteRecebeResposta() {

        assertEquals( ClienteResponse("686.524.400-18", "Cliente Teste1", "cliente1@teste.com"), clienteResponse.body)
    }


    @Given("^que o cliente envia uma solicitacao para recuperar informações de um cliente pelo CPF")
    fun givenClienteEnviaSolicitacaoRecuperarInformacoes() {


        clienteResponse = clienteCucumberClient.findByCpf("123.456.789-09")

    }

    @When("^cliente recebe as informacoes com code status (\\d+)")
    fun whenClienteRecebeInformacao(status:Int) {
        assertEquals(clienteResponse.statusCode.value(), status)
    }


    @Then("recebe as informacoes com a resposta")
    fun thenRecebeInformacoesResposta() {

        assertEquals( ClienteResponse(cpf="123.456.789-09", nome="Cliente Teste", email="cliente@teste.com"), clienteResponse.body)
    }

}