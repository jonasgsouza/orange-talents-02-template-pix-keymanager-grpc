package br.com.zup.edu.integration.request

import br.com.zup.edu.integration.enums.BCBBankAccountType
import br.com.zup.edu.pix.BankAccount
import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountRequest(
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: BCBBankAccountType
) {

    companion object {
        fun from(bankAccount: BankAccount): BankAccountRequest {
            return BankAccountRequest(
                participant = bankAccount.ispb,
                branch = bankAccount.agency,
                accountNumber = bankAccount.number,
                accountType = bankAccount.accountType.toBCBBankAccountType()
            )
        }
    }
}
