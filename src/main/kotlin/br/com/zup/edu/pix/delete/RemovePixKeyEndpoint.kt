package br.com.zup.edu.pix.delete


import br.com.zup.edu.RemovePixKeyRequestGrpc
import br.com.zup.edu.RemovePixKeyResponseGrpc
import br.com.zup.edu.RemovePixKeyServiceGrpc
import br.com.zup.edu.pix.delete.service.RemovePixKeyService
import br.com.zup.edu.pix.delete.service.toRemovePixKeyRequest
import br.com.zup.edu.shared.exception.HandleExceptions
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@HandleExceptions
class RemovePixKeyEndpoint(
    val service: RemovePixKeyService
) : RemovePixKeyServiceGrpc.RemovePixKeyServiceImplBase() {

    override fun removeKey(
        request: RemovePixKeyRequestGrpc?,
        responseObserver: StreamObserver<RemovePixKeyResponseGrpc>?
    ) {
        requireNotNull(request) { "Request must not be null" }
        service.removeKey(request.toRemovePixKeyRequest())
        responseObserver?.onNext(RemovePixKeyResponseGrpc.newBuilder().build())
        responseObserver?.onCompleted()
    }
}