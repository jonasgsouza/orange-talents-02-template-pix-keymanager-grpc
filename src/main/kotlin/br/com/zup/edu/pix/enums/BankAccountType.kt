package br.com.zup.edu.pix.enums

import br.com.zup.edu.AccountTypeGrpc
import br.com.zup.edu.integration.enums.BCBBankAccountType

enum class BankAccountType(val bcbBankAccountType: BCBBankAccountType, val grpcBankAccountType: AccountTypeGrpc) {
    CONTA_CORRENTE(BCBBankAccountType.CACC, AccountTypeGrpc.CONTA_CORRENTE),
    CONTA_POUPANCA(BCBBankAccountType.SVGS, AccountTypeGrpc.CONTA_POUPANCA)
}