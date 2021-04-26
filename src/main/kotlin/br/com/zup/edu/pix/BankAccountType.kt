package br.com.zup.edu.pix

import br.com.zup.edu.pix.registry.service.request.CreatePixKeyAccountType

enum class BankAccountType(val createPixKeyAccountType: CreatePixKeyAccountType) {
    CONTA_CORRENTE(CreatePixKeyAccountType.CACC),
    CONTA_POUPANCA(CreatePixKeyAccountType.SVGS)
}