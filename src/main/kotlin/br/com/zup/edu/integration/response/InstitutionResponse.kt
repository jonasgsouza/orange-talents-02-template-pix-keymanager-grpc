package br.com.zup.edu.integration.response

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class InstitutionResponse(
    @param:JsonProperty("nome") val name: String,
    val ispb: String,
)
