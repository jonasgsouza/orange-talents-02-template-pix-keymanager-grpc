package br.com.zup.edu.integration.response

import br.com.zup.edu.integration.enums.BCBBankAccountType
import br.com.zup.edu.pix.find.service.BankAccountDetails
import br.com.zup.edu.pix.find.service.HolderDetails
import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountResponse(
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: BCBBankAccountType
) {
    fun toBankAccountDetails(holder: HolderDetails): BankAccountDetails {
        return BankAccountDetails(
            institutionName = "institutionName",
            agency = branch,
            number = accountNumber,
            accountType = accountType.toBankAccountType(),
            holder = holder
        )
    }
}
