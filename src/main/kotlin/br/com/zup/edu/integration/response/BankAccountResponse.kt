package br.com.zup.edu.integration.response

import br.com.zup.edu.integration.enums.BCBBankAccountType
import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountResponse(
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: BCBBankAccountType
)
