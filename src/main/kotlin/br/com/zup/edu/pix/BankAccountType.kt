package br.com.zup.edu.pix

import br.com.zup.edu.pix.registry.service.request.BankAccountRequest

enum class BankAccountType(val createPixKeyAccountType: BankAccountRequest.BankAccountType) {
    CONTA_CORRENTE(BankAccountRequest.BankAccountType.CACC),
    CONTA_POUPANCA(BankAccountRequest.BankAccountType.SVGS)
}