package br.com.zup.edu.integration.enums

import br.com.zup.edu.pix.enums.BankAccountType
import io.micronaut.core.annotation.Introspected

@Introspected
enum class BCBBankAccountType(val bankAccountType: BankAccountType) {
    CACC(BankAccountType.CONTA_CORRENTE),
    SVGS(BankAccountType.CONTA_POUPANCA)
}