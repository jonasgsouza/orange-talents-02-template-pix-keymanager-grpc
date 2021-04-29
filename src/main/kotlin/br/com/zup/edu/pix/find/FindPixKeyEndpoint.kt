package br.com.zup.edu.pix.find

import br.com.zup.edu.*
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.find.service.FindPixKeyService
import br.com.zup.edu.shared.exception.HandleExceptions
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
@HandleExceptions
class FindPixKeyEndpoint(
    val findPixKeyService: FindPixKeyService
) : FindPixKeyServiceGrpc.FindPixKeyServiceImplBase() {

    override fun findKey(request: FindPixKeyRequestGrpc?, responseObserver: StreamObserver<FindPixKeyResponseGrpc>?) {
        requireNotNull(request) {"Request must not be null"}
        val pixKey: PixKey = findPixKeyService.findKey(request.toFindPixKeyRequest())
        val response = FindPixKeyResponseGrpc.newBuilder()
            .setPixId(pixKey.uuid.toString())
            .setClientId(pixKey.account.holder.id.toString())
            .setKeyType(KeyTypeGrpc.valueOf(pixKey.keyType.name))
            .setKeyValue(pixKey.keyValue)
            .setClientName(pixKey.account.holder.name)
            .setClientDocument(pixKey.account.holder.document)
            .setAccount(
                BankAccountGrpc.newBuilder()
                    .setInstitutionName(pixKey.account.institutionName)
                    .setAgency(pixKey.account.agency)
                    .setNumber(pixKey.account.number)
                    .setAccountType(AccountTypeGrpc.valueOf(pixKey.account.accountType.name))
            )
            .setCreatedAt(pixKey.createdAt.toGrpcTimestamp())
            .build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}

