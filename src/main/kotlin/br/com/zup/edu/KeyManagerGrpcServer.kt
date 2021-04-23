package br.com.zup.edu

import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
class KeyManagerGrpcServer(
    val keyRepository: PixKeyRepository
) : KeyManagerGrpcServiceGrpc.KeyManagerGrpcServiceImplBase() {

    override fun registerKey(
        request: RegisterKeyRequest?,
        responseObserver: StreamObserver<RegisterKeyResponse>?
    ) {

        if(request == null) throw IllegalArgumentException("Request must not be null")
        val pixKey = PixKey.from(request);
        keyRepository.save(pixKey)
        val response = RegisterKeyResponse.newBuilder().setPixId(pixKey.uuid.toString()).build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
        with(pixKey) {

        }
    }
}