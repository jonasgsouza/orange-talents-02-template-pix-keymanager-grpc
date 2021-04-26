package br.com.zup.edu.pix.registry

import br.com.zup.edu.GrpcRegisterPixKeyRequest
import br.com.zup.edu.GrpcRegisterPixKeyResponse
import br.com.zup.edu.KeyManagerGrpcServiceGrpc
import br.com.zup.edu.pix.PixKeyRepository
import br.com.zup.edu.pix.registry.service.ERPItau
import io.grpc.stub.StreamObserver
import io.micronaut.transaction.SynchronousTransactionManager
import java.lang.IllegalArgumentException
import java.sql.Connection
import javax.inject.Singleton

@Singleton
class RegisterPixKeyEndpoint(
    val keyRepository: PixKeyRepository,
    val erpItau: ERPItau,
    val transactionManager: SynchronousTransactionManager<Connection>
) : KeyManagerGrpcServiceGrpc.KeyManagerGrpcServiceImplBase() {

    override fun registerKey(
        request: GrpcRegisterPixKeyRequest?,
        responseObserver: StreamObserver<GrpcRegisterPixKeyResponse>?
    ) {
        if (request == null) throw IllegalArgumentException("Request must not be null")
        erpItau.findBankAccount(request.clientId, request.accountType.toBankAccountType())
            .subscribe { bankAccountResponse ->
                val pixKey =
                    transactionManager.executeWrite { keyRepository.save(request.toModel(bankAccountResponse.toModel())) }
                val grpcRegisterPixKeyResponse =
                    GrpcRegisterPixKeyResponse.newBuilder().setPixId(pixKey.uuid.toString()).build()
                responseObserver?.onNext(grpcRegisterPixKeyResponse)
                responseObserver?.onCompleted()
            }
    }
}