package br.com.zup.edu.pix.registry

import br.com.zup.edu.GrpcAccountType
import br.com.zup.edu.GrpcKeyType
import br.com.zup.edu.GrpcRegisterPixKeyRequest
import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.BankAccountType
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.PixKeyType
import java.lang.IllegalArgumentException

fun GrpcKeyType.toPixKeyType(): PixKeyType {
    return when(this) {
        GrpcKeyType.CPF -> PixKeyType.CPF
        GrpcKeyType.CNPJ -> PixKeyType.CNPJ
        GrpcKeyType.EMAIL -> PixKeyType.EMAIL
        GrpcKeyType.PHONE -> PixKeyType.PHONE
        GrpcKeyType.RANDOM -> PixKeyType.RANDOM
        GrpcKeyType.UNRECOGNIZED -> throw IllegalArgumentException("Tipo da chave pix inválido")
    }
}

fun GrpcAccountType.toBankAccountType(): BankAccountType {
    return when(this) {
        GrpcAccountType.CONTA_CORRENTE -> BankAccountType.CONTA_CORRENTE
        GrpcAccountType.CONTA_POUPANCA -> BankAccountType.CONTA_POUPANCA
        GrpcAccountType.UNRECOGNIZED -> throw IllegalArgumentException("Tipo da conta inválido")
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