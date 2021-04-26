package br.com.zup.edu.pix.registry.service.request

import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountRequest(
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: CreatePixKeyAccountType
)
