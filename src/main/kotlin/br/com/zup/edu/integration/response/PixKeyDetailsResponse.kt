package br.com.zup.edu.integration.response

import br.com.zup.edu.integration.enums.BCBPixKeyType
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
data class PixKeyDetailsResponse(
    val keyType: BCBPixKeyType,
    val key: String,
    val bankAccount: BankAccountResponse,
    val owner: OwnerResponse,
    val createdAt: LocalDateTime
)
