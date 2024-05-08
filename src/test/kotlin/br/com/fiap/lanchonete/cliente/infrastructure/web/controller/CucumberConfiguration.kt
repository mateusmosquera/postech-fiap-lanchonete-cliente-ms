package br.com.fiap.lanchonete.cliente.infrastructure.web.controller

import br.com.fiap.lanchonete.cliente.LanchoneteClienteApplication
import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@CucumberContextConfiguration
@SpringBootTest(classes = [LanchoneteClienteApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class CucumberConfiguration {
}