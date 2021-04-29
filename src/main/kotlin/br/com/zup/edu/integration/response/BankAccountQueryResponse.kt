package br.com.zup.edu.integration.response

import br.com.zup.edu.integration.enums.ERPItauBankAccountType
import br.com.zup.edu.pix.BankAccount
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountQueryResponse(
    @param:JsonProperty("tipo") val bankAccountType: ERPItauBankAccountType,
    @param:JsonProperty("agencia") val agency: String,
    @param:JsonProperty("numero") val number: String,
    @param:JsonProperty("instituicao") val institution: InstitutionResponse,
    @param:JsonProperty("titular") val holder: HolderResponse
) {
    fun toModel(): BankAccount {
        return BankAccount(
            accountType = bankAccountType.bankAccountType,
            institutionName = institution.name,
            ispb = institution.ispb,
            agency = agency,
            number = number,
            holder = holder.toModel()
        )
    }

}
