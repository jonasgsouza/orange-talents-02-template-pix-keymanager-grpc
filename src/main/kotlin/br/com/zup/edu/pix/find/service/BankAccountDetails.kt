package br.com.zup.edu.pix.find.service

import br.com.zup.edu.pix.enums.BankAccountType
import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountDetails(
    val institutionName: String,
    val agency: String,
    val number: String,
    val accountType: BankAccountType,
    val holder: HolderDetails
)