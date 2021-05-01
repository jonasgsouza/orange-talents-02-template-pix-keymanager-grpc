package br.com.zup.edu.pix.registry

import br.com.zup.edu.AccountTypeGrpc
import br.com.zup.edu.KeyTypeGrpc
import br.com.zup.edu.RegisterPixKeyRequestGrpc
import br.com.zup.edu.pix.enums.BankAccountType
import br.com.zup.edu.pix.enums.PixKeyType
import br.com.zup.edu.pix.registry.service.request.RegisterPixKeyRequest

fun KeyTypeGrpc.toPixKeyType(): PixKeyType {
    return when (this) {
        KeyTypeGrpc.CPF -> PixKeyType.CPF
        KeyTypeGrpc.CNPJ -> PixKeyType.CNPJ
        KeyTypeGrpc.EMAIL -> PixKeyType.EMAIL
        KeyTypeGrpc.PHONE -> PixKeyType.PHONE
        KeyTypeGrpc.RANDOM -> PixKeyType.RANDOM
        KeyTypeGrpc.UNRECOGNIZED -> throw IllegalArgumentException("Invalid keyType")
    }
}

fun AccountTypeGrpc.toBankAccountType(): BankAccountType {
    return when (this) {
        AccountTypeGrpc.CONTA_CORRENTE -> BankAccountType.CONTA_CORRENTE
        AccountTypeGrpc.CONTA_POUPANCA -> BankAccountType.CONTA_POUPANCA
        AccountTypeGrpc.UNRECOGNIZED -> throw IllegalArgumentException("Invalid accountType")
    }
}

fun RegisterPixKeyRequestGrpc.toRegisterPixKeyRequest(): RegisterPixKeyRequest {
    return RegisterPixKeyRequest(
        clientId = this.clientId,
        keyType = this.keyType.toPixKeyType(),
        keyValue = this.keyValue,
        accountType = this.accountType.toBankAccountType()
    )
}
