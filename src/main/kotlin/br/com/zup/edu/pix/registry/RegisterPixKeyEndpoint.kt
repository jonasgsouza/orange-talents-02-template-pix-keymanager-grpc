package br.com.zup.edu.pix.registry

import br.com.zup.edu.RegisterPixKeyRequestGrpc
import br.com.zup.edu.RegisterPixKeyResponseGrpc
import br.com.zup.edu.RegisterPixKeyServiceGrpc
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.registry.service.RegisterPixKeyService
import br.com.zup.edu.pix.registry.service.toRegisterPixKeyRequest
import br.com.zup.edu.shared.exception.HandleExceptions
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@HandleExceptions
class RegisterPixKeyEndpoint(
    val service: RegisterPixKeyService
) : RegisterPixKeyServiceGrpc.RegisterPixKeyServiceImplBase() {

    override fun registerKey(
        request: RegisterPixKeyRequestGrpc?,
        responseObserver: StreamObserver<RegisterPixKeyResponseGrpc>?
    ) {
        requireNotNull(request) { "Request must not be null" }
        val pixKey: PixKey = service.registerKey(request.toRegisterPixKeyRequest())
        val grpcResponse = RegisterPixKeyResponseGrpc.newBuilder().setPixId(pixKey.uuid.toString()).build();
        responseObserver?.onNext(grpcResponse);
        responseObserver?.onCompleted()
    }
}