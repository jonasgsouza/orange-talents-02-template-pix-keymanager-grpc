package br.com.zup.edu.pix.find.service.request

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class InternalFindPixKeyRequest(
    @field:NotBlank
    val keyValue: String
)
