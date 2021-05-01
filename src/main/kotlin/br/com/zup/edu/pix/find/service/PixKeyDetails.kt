package br.com.zup.edu.pix.find.service

import br.com.zup.edu.pix.BankAccount
import br.com.zup.edu.pix.Holder
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.enums.BankAccountType
import br.com.zup.edu.pix.enums.PixKeyType
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime
import java.util.*


@Introspected
data class PixKeyDetails(
    val pixId: UUID,
    val keyType: PixKeyType,
    val keyValue: String?,
    val createdAt: LocalDateTime,
    val account: BankAccountDetails
) {

    companion object {
        fun from(pixKey: PixKey): PixKeyDetails {
            return PixKeyDetails(
                pixId = pixKey.uuid,
                keyType = pixKey.keyType,
                keyValue = pixKey.keyValue,
                createdAt = pixKey.createdAt,
                account = BankAccountDetails.from(pixKey.account)
            )
        }
    }
}

@Introspected
data class BankAccountDetails(
    val institutionName: String,
    val agency: String,
    val number: String,
    val accountType: BankAccountType,
    val holder: HolderDetails
) {
    companion object {
        fun from(bankAccount: BankAccount): BankAccountDetails {
            return BankAccountDetails(
                institutionName = bankAccount.institutionName,
                agency = bankAccount.agency,
                number = bankAccount.number,
                accountType = bankAccount.accountType,
                holder = HolderDetails.from(bankAccount.holder)
            )
        }
    }
}

@Introspected
data class HolderDetails(
    val id: UUID,
    val name: String,
    val document: String
) {
    companion object {
        fun from(holder: Holder): HolderDetails {
            return HolderDetails(
                id = holder.id,
                name = holder.name,
                document = holder.document
            )
        }
    }
}
