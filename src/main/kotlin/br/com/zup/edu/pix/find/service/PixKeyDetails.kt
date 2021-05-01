package br.com.zup.edu.pix.find.service

import br.com.zup.edu.pix.enums.PixKeyType
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime
import java.util.*

@Introspected
data class PixKeyDetails(
    val pixId: UUID? = null,
    val keyType: PixKeyType,
    val keyValue: String? = null,
    val createdAt: LocalDateTime,
    val account: BankAccountDetails
)
