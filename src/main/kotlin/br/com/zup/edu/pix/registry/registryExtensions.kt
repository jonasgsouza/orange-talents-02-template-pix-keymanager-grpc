package br.com.zup.edu.pix.registry.service

import br.com.zup.edu.AccountTypeGrpc
import br.com.zup.edu.KeyTypeGrpc
import br.com.zup.edu.RegisterPixKeyRequestGrpc
import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.registry.service.request.RegisterPixKeyRequest

fun KeyTypeGrpc.toPixKeyType(): PixKey.PixKeyType {
    return when (this) {
        KeyTypeGrpc.CPF -> PixKey.PixKeyType.CPF
        KeyTypeGrpc.CNPJ -> PixKey.PixKeyType.CNPJ
        KeyTypeGrpc.EMAIL -> PixKey.PixKeyType.EMAIL
        KeyTypeGrpc.PHONE -> PixKey.PixKeyType.PHONE
        KeyTypeGrpc.RANDOM -> PixKey.PixKeyType.RANDOM
        KeyTypeGrpc.UNRECOGNIZED -> throw IllegalArgumentException("Invalid keyType")
    }
}

fun AccountTypeGrpc.toBankAccountType(): BankAccount.BankAccountType {
    return when (this) {
        AccountTypeGrpc.CONTA_CORRENTE -> BankAccount.BankAccountType.CONTA_CORRENTE
        AccountTypeGrpc.CONTA_POUPANCA -> BankAccount.BankAccountType.CONTA_POUPANCA
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
