package br.com.zup.edu.pix.registry.service.request

import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.shared.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import io.micronaut.validation.validator.constraints.EmailValidator
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class RegisterPixKeyRequest(
    @field:NotBlank
    @field:ValidUUID
    val clientId: String,

    @field:NotNull
    @Email
    val keyType: PixKey.PixKeyType,

    @field:NotNull
    val accountType: BankAccount.BankAccountType,

    @field:NotBlank
    val keyValue: String?,
) {
    fun toModel(account: BankAccount): PixKey {
        return PixKey(
            clientId = clientId,
            keyType = keyType,
            keyValue = keyValue,
            account = account
        )
    }

    fun isKeyValueValid(): Boolean {
        return when(keyType) {
            PixKey.PixKeyType.CPF -> keyValue?.let { Regex.fromLiteral("^[0-9]{11}\$").matches(it) } ?: false
            PixKey.PixKeyType.CNPJ -> TODO()
            PixKey.PixKeyType.PHONE -> keyValue?.let { Regex.fromLiteral("^\\+[1-9][0-9]\\d{1,14}\$").matches(it) } ?: false
            PixKey.PixKeyType.EMAIL -> EmailValidator().run {
                initialize(null)
                isValid(keyValue, null)
            }
            PixKey.PixKeyType.RANDOM -> keyValue == null || keyValue.isBlank()
        }
    }
}
