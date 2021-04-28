package br.com.zup.edu.integration.request

import br.com.zup.edu.pix.PixKey
import io.micronaut.core.annotation.Introspected

@Introspected
data class DeletePixKeyRequest(
    val key: String,
    val participant: String
) {
    companion object {
        fun of(pixKey: PixKey): DeletePixKeyRequest {
            return DeletePixKeyRequest(
                key = pixKey.keyValue!!,
                participant = pixKey.account.ispb
            )
        }
    }
}
