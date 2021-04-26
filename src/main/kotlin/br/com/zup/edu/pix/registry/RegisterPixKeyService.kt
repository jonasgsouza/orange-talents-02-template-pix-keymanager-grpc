package br.com.zup.edu.pix.registry

import br.com.zup.edu.GrpcRegisterPixKeyRequest
import br.com.zup.edu.GrpcRegisterPixKeyResponse
import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.PixKeyRepository
import br.com.zup.edu.pix.registry.service.ERPItau
import io.grpc.stub.StreamObserver
import io.micronaut.validation.Validated
import io.reactivex.Maybe
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
@Validated
class RegisterPixKeyService(
    val keyRepository: PixKeyRepository,
    val erpItau: ERPItau
) {

    @Transactional
    fun registerKey(@Valid request: RegisterPixKeyRequest): Maybe<PixKey> {
        return erpItau.findBankAccount(request.clientId, request.accountType)
            .map { bankAccountResponse ->
                val pixKey = request.toModel(bankAccountResponse.toModel())
                keyRepository.save(pixKey)
                pixKey
            }
    }
}