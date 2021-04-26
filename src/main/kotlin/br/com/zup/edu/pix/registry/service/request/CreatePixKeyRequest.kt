package br.com.zup.edu.pix.registry.service.request

import io.micronaut.core.annotation.Introspected

@Introspected
data class CreatePixKeyRequest(
    val keyType: CreatePixKeyType,
    val key: String,
    val bankAccount: BankAccountRequest,
    val owner: OwnerRequest
)
