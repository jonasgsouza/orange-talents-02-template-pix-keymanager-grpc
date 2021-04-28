package br.com.zup.edu.pix.delete


import br.com.zup.edu.GrpcRemovePixKeyRequest
import br.com.zup.edu.GrpcRemovePixKeyResponse
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
        request: GrpcRemovePixKeyRequest?,
        responseObserver: StreamObserver<GrpcRemovePixKeyResponse>?
    ) {
        requireNotNull(request) { "Request must not be null" }
        service.removeKey(request.toRemovePixKeyRequest())
        responseObserver?.onNext(GrpcRemovePixKeyResponse.newBuilder().build())
        responseObserver?.onCompleted()
    }
}