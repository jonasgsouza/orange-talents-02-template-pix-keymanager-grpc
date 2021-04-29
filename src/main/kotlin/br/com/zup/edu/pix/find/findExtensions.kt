package br.com.zup.edu.pix.find

import br.com.zup.edu.FindPixKeyRequestGrpc
import br.com.zup.edu.pix.find.service.request.FindPixKeyRequest
import com.google.protobuf.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

fun FindPixKeyRequestGrpc.toFindPixKeyRequest(): FindPixKeyRequest {
    return FindPixKeyRequest(
        clientId = UUID.fromString(clientId),
        pixId = UUID.fromString(pixId)
    )
}

fun LocalDateTime.toGrpcTimestamp(): Timestamp {
    val instant: Instant = toInstant(ZoneOffset.UTC);

    return Timestamp.newBuilder()
        .setSeconds(instant.epochSecond)
        .setNanos(instant.nano)
        .build();
}