package br.com.zup.edu.integration.request

import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountRequest(
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: BankAccountType
) {
    enum class BankAccountType {
        CACC, SVGS
    }
}
