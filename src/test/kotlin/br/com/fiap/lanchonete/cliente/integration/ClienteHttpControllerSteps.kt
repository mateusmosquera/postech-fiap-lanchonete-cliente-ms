package br.com.fiap.lanchonete.cliente.integration

import br.com.fiap.lanchonete.cliente.application.dto.request.ClienteRequest
import br.com.fiap.lanchonete.cliente.application.dto.response.ClienteResponse
import br.com.fiap.lanchonete.cliente.client.ClienteCucumberClient
import feign.FeignException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ClienteHttpControllerSteps(var clienteCucumberClient: ClienteCucumberClient){

    private lateinit var clienteRequest: ClienteRequest
    private lateinit var clienteResponse: ResponseEntity<ClienteResponse>

    @Given("^que o cliente envia uma solicitacao para criar um novo cliente, com cpf \"([^\"]*)\", com nome ([^\"]*) e email ([^\"]*)")
    fun givenClienteRequestIsSentToCreateANewClient(cpf:String, nome:String, email:String) {

        clienteRequest = ClienteRequest(cpf, nome, email)
        try {
            clienteResponse = clienteCucumberClient.create(clienteRequest)
        } catch (e:FeignException){
            clienteResponse = ResponseEntity(HttpStatus.valueOf(e.status()))
        }


    }

    @When("^cliente recebe a solicitacao com code status (\\d+)")
    fun whenClienteRecebeSolicitacao(status:Int) {
        assertEquals(clienteResponse.statusCode.value(), status)
    }


    @Then("^o cliente recebe a resposta, com cpf \"([^\"]*)\", com nome ([^\"]*) e email ([^\"]*)")
    fun thenClienteRecebeResposta(cpf:String, nome:String, email:String) {

        if(clienteResponse.statusCode.value() == 201) {
            assertEquals( ClienteResponse(cpf, nome, email), clienteResponse.body)
        }
    }


    @Given("^que o cliente envia uma solicitacao para recuperar informacoes de um cliente pelo CPF")
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