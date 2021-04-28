package br.com.zup.edu.integration.request

import br.com.zup.edu.pix.PixKey
import io.micronaut.core.annotation.Introspected

@Introspected
data class CreatePixKeyRequest(
    val keyType: PixKeyType,
    val key: String?,
    val bankAccount: BankAccountRequest,
    val owner: OwnerRequest
) {

    companion object {
        fun of(pixKey: PixKey): CreatePixKeyRequest {
            return CreatePixKeyRequest(
                keyType = pixKey.keyType.bcbPixKeyType,
                key = pixKey.keyValue,
                bankAccount = pixKey.account.toBankAccountRequest(),
                owner = pixKey.account.holder.toOwnerRequest()
            )
        }
    }
    enum class PixKeyType {
        CPF, CNPJ, PHONE, EMAIL, RANDOM
    }
}
