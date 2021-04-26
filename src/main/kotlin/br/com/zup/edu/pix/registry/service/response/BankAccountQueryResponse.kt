package br.com.zup.edu.pix.registry.service.response

import br.com.zup.edu.pix.BankAccount
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class BankAccountQueryResponse(
    @param:JsonProperty("tipo") val type: BankAccountType,
    @param:JsonProperty("agencia") val agency: String,
    @param:JsonProperty("numero") val number: String,
    @param:JsonProperty("instituicao") val institution: InstitutionResponse,
    @param:JsonProperty("titular") val holder: HolderResponse
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

    enum class BankAccountType(
        val bankAccountType: br.com.zup.edu.pix.BankAccountType
    ) {
        CONTA_CORRENTE(br.com.zup.edu.pix.BankAccountType.CONTA_CORRENTE),
        CONTA_POUPANCA(br.com.zup.edu.pix.BankAccountType.CONTA_POUPANCA);
    }
}
