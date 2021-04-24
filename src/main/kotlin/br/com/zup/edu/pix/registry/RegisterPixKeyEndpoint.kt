package br.com.zup.edu.pix.registry

import br.com.zup.edu.KeyManagerGrpcServiceGrpc
import br.com.zup.edu.RegisterPixKeyRequest
import br.com.zup.edu.RegisterPixKeyResponse
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.PixKeyRepository
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
class RegisterPixKeyEndpoint(
    val keyRepository: PixKeyRepository
) : KeyManagerGrpcServiceGrpc.KeyManagerGrpcServiceImplBase() {

    override fun registerKey(
        request: RegisterPixKeyRequest?,
        responseObserver: StreamObserver<RegisterPixKeyResponse>?
    ) {

        if(request == null) throw IllegalArgumentException("Request must not be null")
        val pixKey = PixKey.from(request);
        keyRepository.save(pixKey)
        val response = RegisterPixKeyResponse.newBuilder().setPixId(pixKey.uuid.toString()).build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
        with(pixKey) {

        }
    }
}