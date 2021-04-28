package br.com.zup.edu.pix.registry.service.request

import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.PixKey
import br.com.zup.shared.validation.ValidPixKey
import br.com.zup.shared.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import io.micronaut.validation.validator.constraints.EmailValidator
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
@ValidPixKey
data class RegisterPixKeyRequest(
    @field:NotBlank
    @field:ValidUUID
    val clientId: String,

    @field:NotNull
    val keyType: PixKey.PixKeyType,

    @field:NotNull
    val accountType: BankAccount.BankAccountType,

    val keyValue: String?,
) {
    fun toModel(account: BankAccount): PixKey {
        return PixKey(
            clientId = clientId,
            keyType = keyType,
            keyValue = if(keyType == PixKey.PixKeyType.RANDOM) UUID.randomUUID().toString() else keyValue,
            account = account
        )
    }

    fun isKeyValueValid(): Boolean {
        return when(keyType) {
            PixKey.PixKeyType.CPF -> keyValue?.matches("^[0-9]{11}\$".toRegex()) ?: false
            PixKey.PixKeyType.CNPJ -> TODO()
            PixKey.PixKeyType.PHONE -> keyValue?.matches("^\\+[1-9][0-9]\\d{1,14}\$".toRegex()) ?: false
            PixKey.PixKeyType.EMAIL -> EmailValidator().run {
                initialize(null)
                isValid(keyValue, null)
            }
            PixKey.PixKeyType.RANDOM -> keyValue.isNullOrBlank()
        }
    }
}
