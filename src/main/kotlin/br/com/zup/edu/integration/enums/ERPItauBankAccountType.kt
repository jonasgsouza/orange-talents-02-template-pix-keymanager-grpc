package br.com.zup.edu.integration.enums

import br.com.zup.edu.pix.BankAccount

enum class ERPItauBankAccountType(
    val bankAccountType: BankAccount.BankAccountType
) {
    CONTA_CORRENTE(BankAccount.BankAccountType.CONTA_CORRENTE),
    CONTA_POUPANCA(BankAccount.BankAccountType.CONTA_POUPANCA);
}