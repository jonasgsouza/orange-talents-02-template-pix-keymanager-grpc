package br.com.zup.edu.pix.enums

import br.com.zup.edu.integration.enums.BCBBankAccountType

enum class BankAccountType(val bcbBankAccountType: BCBBankAccountType) {
    CONTA_CORRENTE(BCBBankAccountType.CACC),
    CONTA_POUPANCA(BCBBankAccountType.SVGS)
}