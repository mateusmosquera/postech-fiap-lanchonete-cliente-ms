package br.com.fiap.lanchonete.cliente.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.security.MessageDigest
import java.time.LocalDateTime
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_id_seq")
    @SequenceGenerator(name = "cliente_id_seq", sequenceName = "cliente_id_seq", allocationSize = 1)
    val id: Long?,
    @Column(name = "CPF", unique = true)
    var cpf: String?,
    @Column(name = "NOME")
    var nome: String?,
    @Column(name = "EMAIL", unique = true)
    var email: String?,
    @Column(name = "ATIVO")
    var ativo: Boolean? = true,
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    var createDate: LocalDateTime? = null,
    @LastModifiedDate
    @Column(name = "update_date", nullable = false, updatable = false)
    var updateDate: LocalDateTime? = null){

    fun logicalRemove(){
        this.nome = nome?.let { hashString(it) }
        this.email = email?.let { hashString(it) }
        this.cpf = cpf?.let { hashString(it) }
        this.ativo = false
    }

    private fun hashString(input: String): String {
        val inputString = UUID.randomUUID().toString() + input
        val bytes = MessageDigest.getInstance("SHA-256").digest(inputString.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}