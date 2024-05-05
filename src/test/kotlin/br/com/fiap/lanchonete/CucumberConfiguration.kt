package br.com.fiap.lanchonete

import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@CucumberContextConfiguration
@SpringBootTest(classes = [LanchoneteApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class CucumberConfiguration {
}