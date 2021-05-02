package br.com.zup.edu.pix.enums

import br.com.zup.edu.AccountTypeGrpc
import br.com.zup.edu.integration.enums.BCBBankAccountType

enum class BankAccountType {
    CONTA_CORRENTE,
    CONTA_POUPANCA;

    fun toBCBBankAccountType(): BCBBankAccountType {
        return when (this) {
            CONTA_CORRENTE -> BCBBankAccountType.CACC
            CONTA_POUPANCA -> BCBBankAccountType.SVGS
        }
    }

    fun toAccountTypeGrpc(): AccountTypeGrpc {
        return when (this) {
            CONTA_CORRENTE -> AccountTypeGrpc.CONTA_CORRENTE
            CONTA_POUPANCA -> AccountTypeGrpc.CONTA_POUPANCA
        }
    }
}