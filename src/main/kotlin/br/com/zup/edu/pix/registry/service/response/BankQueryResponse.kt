package br.com.zup.edu.pix.registry.service.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class BankQueryResponse(
    @param:JsonProperty("nome") val name: String,
    val ispb: String
)