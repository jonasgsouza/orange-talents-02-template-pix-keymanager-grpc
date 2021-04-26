package br.com.zup.edu.pix.registry.service.response

import br.com.zup.edu.pix.BankAccountType

enum class BankAccountTypeQueryResponse(
    val bankAccountType: BankAccountType
) {
    CONTA_CORRENTE(BankAccountType.CONTA_CORRENTE),
    CONTA_POUPANCA(BankAccountType.CONTA_POUPANCA);

    fun toBankAccountType(): BankAccountType {
        return bankAccountType
    }
}