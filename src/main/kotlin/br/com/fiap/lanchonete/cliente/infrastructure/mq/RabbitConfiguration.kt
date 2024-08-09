package br.com.fiap.lanchonete.cliente.infrastructure.mq

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Files
import kotlin.io.path.Path

@Configuration
class RabbitConfiguration(@Value("\${app.mq.port}") private val port: Int,
                          @Value("\${app.mq.cliente.username}") private val username: String,
                          @Value("\${app.mq.cliente.password}") private val password: String,
                          @Value("\${app.mq.cliente.brokerURL}") private val host: String) {



    @Bean
    fun connectionFactory(): CachingConnectionFactory {
        val secretPath = Path(password)
        val connectionFactory = CachingConnectionFactory(host)
        connectionFactory.username = username
        connectionFactory.setPassword(Files.readString(secretPath))
        connectionFactory.port = port
        return connectionFactory
    }

    @Bean
    fun rabbitTemplate(connectionFactory: CachingConnectionFactory?): RabbitTemplate {
        return RabbitTemplate(connectionFactory!!)
    }
}