package br.com.zup.edu.pix.find

import br.com.zup.edu.FindPixKeyRequestGrpc
import br.com.zup.edu.FindPixKeyResponseGrpc
import br.com.zup.edu.FindPixKeyServiceGrpc
import br.com.zup.edu.pix.find.service.FindPixKeyService
import br.com.zup.edu.pix.find.service.PixKeyDetails
import br.com.zup.edu.shared.exception.HandleExceptions
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@HandleExceptions
class FindPixKeyEndpoint(
    val findPixKeyService: FindPixKeyService
) : FindPixKeyServiceGrpc.FindPixKeyServiceImplBase() {

    override fun findKey(request: FindPixKeyRequestGrpc?, responseObserver: StreamObserver<FindPixKeyResponseGrpc>?) {
        requireNotNull(request) { "Request must not be null" }
        val pixKeyDetails: PixKeyDetails = findPixKeyService.findKey(request.toPixKeyFinder())
        val response: FindPixKeyResponseGrpc = findPixKeyResponseGrpcFrom(pixKeyDetails)
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}

