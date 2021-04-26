package br.com.zup.edu.pix.registry

import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.PixKey
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class RegisterPixKeyRequest(
    @field:NotBlank
    val clientId: String,

    @field:NotNull
    val keyType: PixKey.PixKeyType,

    @field:NotNull
    val accountType: BankAccount.BankAccountType,

    @field:NotBlank
    val keyValue: String,
) {
    fun toModel(account: BankAccount): PixKey {
        return PixKey(
            clientId = clientId,
            keyType = keyType,
            keyValue = keyValue,
            account = account
        )
    }
}
