package br.com.zup.edu.pix.delete

import br.com.zup.edu.RemovePixKeyRequestGrpc
import br.com.zup.edu.pix.delete.service.request.RemovePixKeyRequest

fun RemovePixKeyRequestGrpc.toRemovePixKeyRequest(): RemovePixKeyRequest {
    return RemovePixKeyRequest(
        clientId = clientId,
        pixId = pixId
    )
}