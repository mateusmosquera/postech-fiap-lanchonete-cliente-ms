package br.com.fiap.lanchonete.cliente.exception.enums

import br.com.fiap.lanchonete.cliente.exception.dto.ResponseErrorDto

interface ExceptionEnum {
    fun getResponseError(): ResponseErrorDto
}