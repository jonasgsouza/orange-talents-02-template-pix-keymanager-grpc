package br.com.zup.edu.pix.registry.service.request

import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.enums.BankAccountType
import br.com.zup.edu.pix.enums.PixKeyType
import br.com.zup.edu.shared.validation.ValidPixKey
import br.com.zup.edu.shared.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import io.micronaut.validation.validator.constraints.EmailValidator
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
@ValidPixKey
data class RegisterPixKeyRequest(
    @field:NotBlank
    @field:ValidUUID
    val clientId: String?,

    @field:NotNull
    val keyType: PixKeyType?,

    @field:NotNull
    val accountType: BankAccountType?,

    @field:Size(max = 77)
    val keyValue: String?,
) {
    fun toModel(account: BankAccount): PixKey {
        requireNotNull(keyType) { "Key type must not be null" }
        return PixKey(
            keyType = keyType,
            keyValue = if (keyType == PixKeyType.RANDOM) UUID.randomUUID().toString() else keyValue,
            account = account
        )
    }

    fun isKeyValueValid(): Boolean {
        requireNotNull(keyType) { "Key type must not be null" }
        return when (keyType) {
            PixKeyType.CPF -> keyValue?.matches("^[0-9]{11}\$".toRegex()) ?: false
            PixKeyType.CNPJ -> TODO()
            PixKeyType.PHONE -> keyValue?.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex()) ?: false
            PixKeyType.EMAIL -> EmailValidator().run {
                initialize(null)
                isValid(keyValue, null)
            }
            PixKeyType.RANDOM -> keyValue.isNullOrBlank()
        }
    }
}
