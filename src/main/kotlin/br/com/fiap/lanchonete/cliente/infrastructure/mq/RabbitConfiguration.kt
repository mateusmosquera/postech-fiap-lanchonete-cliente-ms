package br.com.fiap.lanchonete.cliente.infrastructure.mq

import com.rabbitmq.client.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfiguration(@Value("\${app.mq.cliente.username}") private val username: String,
                          @Value("\${app.mq.cliente.password}") private val password: String,
                          @Value("\${app.mq.cliente.brokerURL}") private val host: String,
                          @Value("\${app.mq.port}") private val port: Int,
                          @Value("\${app.mq.ssl}") private val ssl: Boolean ) {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = ConnectionFactory()
        connectionFactory.username = username
        connectionFactory.password = password
        connectionFactory.host = host
        connectionFactory.port = port
        if(ssl){
            connectionFactory.useSslProtocol()
        }
        return connectionFactory
    }
}