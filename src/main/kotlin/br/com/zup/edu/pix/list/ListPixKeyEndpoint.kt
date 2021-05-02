package br.com.zup.edu.pix.list

import br.com.zup.edu.ListPixKeyDetailsGrpc
import br.com.zup.edu.ListPixKeyRequestGrpc
import br.com.zup.edu.ListPixKeyResponseGrpc
import br.com.zup.edu.ListPixKeyServiceGrpc
import br.com.zup.edu.pix.find.toGrpcTimestamp
import br.com.zup.edu.pix.repository.PixKeyRepository
import br.com.zup.edu.shared.exception.HandleExceptions
import io.grpc.stub.StreamObserver
import java.util.*
import javax.inject.Singleton

@Singleton
@HandleExceptions
class ListPixKeyEndpoint(
    val repository: PixKeyRepository
) : ListPixKeyServiceGrpc.ListPixKeyServiceImplBase() {

    override fun findKeys(request: ListPixKeyRequestGrpc?, responseObserver: StreamObserver<ListPixKeyResponseGrpc>?) {
        requireNotNull(request) { "Request must not be null" }
        val responseBuilder: ListPixKeyResponseGrpc.Builder = ListPixKeyResponseGrpc.newBuilder()
        repository.findByAccountHolderId(UUID.fromString(request.clientId))
            .map { pixKey ->
                ListPixKeyDetailsGrpc.newBuilder()
                    .setPixId(pixKey.uuid.toString())
                    .setClientId(pixKey.account.holder.id.toString())
                    .setKeyType(pixKey.keyType.toKeyTypeGrpc())
                    .setKeyValue(pixKey.keyValue)
                    .setAccountType(pixKey.account.accountType.toAccountTypeGrpc())
                    .setCreatedAt(pixKey.createdAt.toGrpcTimestamp())
            }
            .forEach { listPixKeyDetails -> responseBuilder.addKey(listPixKeyDetails) }
        responseObserver?.onNext(responseBuilder.build())
        responseObserver?.onCompleted()
    }
}