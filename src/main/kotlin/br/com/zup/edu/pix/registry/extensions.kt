package br.com.zup.edu.pix.registry

import br.com.zup.edu.GrpcAccountType
import br.com.zup.edu.GrpcKeyType
import br.com.zup.edu.GrpcRegisterPixKeyRequest
import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.PixKey
import java.lang.IllegalArgumentException

fun GrpcKeyType.toPixKeyType(): PixKey.PixKeyType {
    return when(this) {
        GrpcKeyType.CPF -> PixKey.PixKeyType.CPF
        GrpcKeyType.CNPJ -> PixKey.PixKeyType.CNPJ
        GrpcKeyType.EMAIL -> PixKey.PixKeyType.EMAIL
        GrpcKeyType.PHONE -> PixKey.PixKeyType.PHONE
        GrpcKeyType.RANDOM -> PixKey.PixKeyType.RANDOM
        GrpcKeyType.UNRECOGNIZED -> throw IllegalArgumentException("Tipo da chave pix inválido")
    }
}

fun GrpcAccountType.toBankAccountType(): BankAccount.BankAccountType {
    return when(this) {
        GrpcAccountType.CONTA_CORRENTE -> BankAccount.BankAccountType.CONTA_CORRENTE
        GrpcAccountType.CONTA_POUPANCA -> BankAccount.BankAccountType.CONTA_POUPANCA
        GrpcAccountType.UNRECOGNIZED -> throw IllegalArgumentException("Tipo da conta inválido")
    }
}

fun GrpcRegisterPixKeyRequest.toRegisterPixKeyRequest(): RegisterPixKeyRequest {
    return RegisterPixKeyRequest(
        clientId = this.clientId,
        keyType = this.keyType.toPixKeyType(),
        keyValue = this.keyValue,
        accountType = this.accountType.toBankAccountType()
    )
}
