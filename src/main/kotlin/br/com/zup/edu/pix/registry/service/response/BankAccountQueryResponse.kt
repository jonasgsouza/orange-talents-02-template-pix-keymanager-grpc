package br.com.zup.edu.pix.registry.service.response

import br.com.zup.edu.pix.BankAccount
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountQueryResponse(
    @param:JsonProperty("tipo") val type: BankAccountTypeQueryResponse,
    @param:JsonProperty("agencia") val agency: String,
    @param:JsonProperty("numero") val number: String,
    @param:JsonProperty("instituicao") val institution: InstitutionResponse,
    @param:JsonProperty("titular") val holder: HolderQueryResponse
) {
    fun toModel(): BankAccount {
        return BankAccount(
            type = type.bankAccountType,
            ispb = institution.ispb,
            agency = agency,
            number = number,
            holder = holder.toModel()
        )
    }
}
