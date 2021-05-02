package br.com.zup.edu.pix.find

import br.com.zup.edu.*
import br.com.zup.edu.FindPixKeyRequestGrpc.FilterCase.*
import br.com.zup.edu.pix.find.service.PixKeyDetails
import br.com.zup.edu.pix.find.service.PixKeyFinder
import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

fun FindPixKeyRequestGrpc.toPixKeyFinder(): PixKeyFinder {
    return when (filterCase) {
        PIXID -> pixId.let { pixIdFilter ->
            PixKeyFinder.FindPixKeyByPixIdAndClientId(
                clientId = pixIdFilter.clientId,
                pixId = pixIdFilter.pixId
            )
        }
        KEYVALUE -> keyValue.let { keyValue -> PixKeyFinder.FindPixKeyByValue(keyValue = keyValue) }
        FILTER_NOT_SET -> throw IllegalArgumentException("Invalid arguments")
    }
}

fun LocalDateTime.toGrpcTimestamp(): Timestamp {
    val instant: Instant = toInstant(ZoneOffset.UTC);

    return Timestamp.newBuilder()
        .setSeconds(instant.epochSecond)
        .setNanos(instant.nano)
        .build();
}

fun findPixKeyResponseGrpcFrom(pixKeyDetails: PixKeyDetails): FindPixKeyResponseGrpc {
    return FindPixKeyResponseGrpc.newBuilder()
        .setPixId(pixKeyDetails.pixId?.toString() ?: "")
        .setKeyType(KeyTypeGrpc.valueOf(pixKeyDetails.keyType.name))
        .setKeyValue(pixKeyDetails.keyValue)
        .setAccount(
            BankAccountGrpc.newBuilder()
                .setInstitutionName(pixKeyDetails.account.institutionName)
                .setAgency(pixKeyDetails.account.agency)
                .setNumber(pixKeyDetails.account.number)
                .setAccountType(AccountTypeGrpc.valueOf(pixKeyDetails.account.accountType.name))
                .setHolder(
                    HolderGrpc.newBuilder()
                        .setId(pixKeyDetails.account.holder.id?.toString() ?: "")
                        .setName(pixKeyDetails.account.holder.name)
                        .setDocument(pixKeyDetails.account.holder.document)
                )
        )
        .setCreatedAt(pixKeyDetails.createdAt.toGrpcTimestamp())
        .build()
}