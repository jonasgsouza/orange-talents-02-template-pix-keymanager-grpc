package br.com.zup.edu.integration.enums

import br.com.zup.edu.pix.enums.BankAccountType

enum class ERPItauBankAccountType(
    val bankAccountType: BankAccountType
) {
    CONTA_CORRENTE(BankAccountType.CONTA_CORRENTE),
    CONTA_POUPANCA(BankAccountType.CONTA_POUPANCA);
}