package br.com.zup.edu.pix.registry

import br.com.zup.edu.GrpcRegisterPixKeyRequest
import br.com.zup.edu.GrpcRegisterPixKeyResponse
import br.com.zup.edu.KeyManagerGrpcServiceGrpc
import br.com.zup.edu.pix.PixKey
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
class RegisterPixKeyEndpoint(
    val registerPixKeyService: RegisterPixKeyService
) : KeyManagerGrpcServiceGrpc.KeyManagerGrpcServiceImplBase() {

    override fun registerKey(
        request: GrpcRegisterPixKeyRequest?,
        responseObserver: StreamObserver<GrpcRegisterPixKeyResponse>?
    ) {
        requireNotNull(request) { "Request must not be null" }
        val pixKey: PixKey = registerPixKeyService.registerKey(request.toRegisterPixKeyRequest())
        val grpcResponse = GrpcRegisterPixKeyResponse.newBuilder().setPixId(pixKey.uuid.toString()).build();
        responseObserver?.onNext(grpcResponse);
        responseObserver?.onCompleted()
    }
}