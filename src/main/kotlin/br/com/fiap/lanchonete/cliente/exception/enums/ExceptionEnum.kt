package br.com.fiap.lanchonete.cliente.exception.enums

import br.com.fiap.lanchonete.cliente.exception.dto.ResponseErrorDto

fun interface ExceptionEnum {
    fun getResponseError(): ResponseErrorDto
}