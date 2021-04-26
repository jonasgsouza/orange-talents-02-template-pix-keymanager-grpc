package br.com.zup.edu.pix.registry

import br.com.zup.edu.GrpcAccountType
import br.com.zup.edu.GrpcKeyType
import br.com.zup.edu.GrpcRegisterPixKeyRequest
import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.BankAccountType
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.PixKeyType

fun GrpcKeyType.toPixKeyType(): PixKeyType {
    return when(this) {
        GrpcKeyType.CPF -> PixKeyType.CPF
        GrpcKeyType.EMAIL -> PixKeyType.EMAIL
        GrpcKeyType.PHONE -> PixKeyType.PHONE
        GrpcKeyType.RANDOM_KEY -> PixKeyType.RANDOM_KEY
        else -> PixKeyType.UNKNOWN
    }
}

fun GrpcAccountType.toBankAccountType(): BankAccountType {
    return when(this) {
        GrpcAccountType.CONTA_CORRENTE -> BankAccountType.CONTA_CORRENTE
        GrpcAccountType.CONTA_POUPANCA -> BankAccountType.CONTA_POUPANCA
        else -> BankAccountType.UNKNOWN
    }
}

fun GrpcRegisterPixKeyRequest.toRegisterPixKeyRequest(): RegisterPixKeyRequest {
    return RegisterPixKeyRequest(
        clientId = this.clientId,
        type = this.type.toPixKeyType(),
        value = this.value,
        accountType = this.accountType.toBankAccountType()
    )
}

fun GrpcRegisterPixKeyRequest.toModel(account: BankAccount): PixKey {
    return PixKey(
        clientId = this.clientId,
        type = this.type.toPixKeyType(),
        value = this.value,
        account = account
    )
}