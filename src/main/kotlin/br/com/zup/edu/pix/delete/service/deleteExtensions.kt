package br.com.zup.edu.pix.delete.service

import br.com.zup.edu.GrpcRemovePixKeyRequest
import br.com.zup.edu.pix.delete.service.request.RemovePixKeyRequest
import java.util.*

fun GrpcRemovePixKeyRequest.toRemovePixKeyRequest(): RemovePixKeyRequest {
    return RemovePixKeyRequest(
        clientId = UUID.fromString(clientId),
        pixId = UUID.fromString(pixId)
    )
}