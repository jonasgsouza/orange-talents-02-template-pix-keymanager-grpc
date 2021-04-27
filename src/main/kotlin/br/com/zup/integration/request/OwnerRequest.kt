package br.com.zup.integration.request

import io.micronaut.core.annotation.Introspected

@Introspected
data class OwnerRequest(
    val type: OwnerType,
    val name: String,
    val taxIdNumber: String
) {
    enum class OwnerType {
        NATURAL_PERSON, LEGAL_PERSON
    }
}
