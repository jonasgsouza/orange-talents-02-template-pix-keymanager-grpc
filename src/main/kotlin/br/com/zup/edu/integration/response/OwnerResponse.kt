package br.com.zup.edu.integration.response

import br.com.zup.edu.integration.enums.OwnerType
import io.micronaut.core.annotation.Introspected

@Introspected
data class OwnerResponse(
    val type: OwnerType,
    val name: String,
    val taxIdNumber: String
)
