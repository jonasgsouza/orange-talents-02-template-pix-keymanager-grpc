package br.com.zup.edu.integration.enums

import br.com.zup.edu.pix.enums.BankAccountType
import io.micronaut.core.annotation.Introspected

@Introspected
enum class BCBBankAccountType {
    CACC,
    SVGS;

    fun toBankAccountType(): BankAccountType {
        return when (this) {
            CACC -> BankAccountType.CONTA_CORRENTE
            SVGS -> BankAccountType.CONTA_POUPANCA
        }
    }
}