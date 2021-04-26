package br.com.zup.edu.pix.registry

import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.BankAccountType
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.PixKeyType
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class RegisterPixKeyRequest(
    @field:NotBlank
    val clientId: String,

    @field:NotNull
    val type: PixKeyType,

    @field:NotNull
    val accountType: BankAccountType,

    @field:NotBlank
    val value: String,
) {
    fun toModel(account: BankAccount): PixKey {
        return PixKey(
            clientId = clientId,
            type = type,
            value = value,
            account = account
        )
    }
}
