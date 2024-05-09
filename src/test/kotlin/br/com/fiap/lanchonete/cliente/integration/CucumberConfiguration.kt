package br.com.fiap.lanchonete.cliente.integration

import br.com.fiap.lanchonete.cliente.LanchoneteClienteApplication
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@CucumberContextConfiguration
@SpringBootTest(classes = [LanchoneteClienteApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
class CucumberConfiguration {
}