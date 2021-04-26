package br.com.zup.edu.pix.registry.service.request

import io.micronaut.core.annotation.Introspected

@Introspected
data class OwnerRequest(
    val type: OwnerType,
    val name: String,
    val taxIdNumber: String
)
