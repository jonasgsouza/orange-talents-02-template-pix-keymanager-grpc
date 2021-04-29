package br.com.zup.edu.integration.request

import br.com.zup.edu.integration.enums.BCBPixKeyType
import br.com.zup.edu.pix.PixKey
import io.micronaut.core.annotation.Introspected

@Introspected
data class CreatePixKeyRequest(
    val keyType: BCBPixKeyType,
    val key: String?,
    val bankAccount: BankAccountRequest,
    val owner: OwnerRequest
) {

    companion object {
        fun from(pixKey: PixKey): CreatePixKeyRequest {
            return CreatePixKeyRequest(
                keyType = pixKey.keyType.bcbPixKeyType,
                key = pixKey.keyValue,
                bankAccount = BankAccountRequest.from(pixKey.account),
                owner = OwnerRequest.from(pixKey.account.holder)
            )
        }
    }

}
