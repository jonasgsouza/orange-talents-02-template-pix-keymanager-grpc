package br.com.zup.edu.pix.registry

import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.PixKeyRepository
import br.com.zup.edu.pix.registry.service.BCB
import br.com.zup.edu.pix.registry.service.ERPItau
import br.com.zup.edu.pix.registry.service.response.BankAccountQueryResponse
import io.micronaut.validation.Validated
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
@Validated
class RegisterPixKeyService(
    val keyRepository: PixKeyRepository,
    val erpItau: ERPItau,
    val bcb: BCB
) {

    @Transactional
    fun registerKey(@Valid request: RegisterPixKeyRequest): PixKey {
        if(keyRepository.existsByKeyValue(request.keyValue)) throw IllegalArgumentException("Pix Key already exists")
        val bankAccountResponse: BankAccountQueryResponse = erpItau.findBankAccount(request.clientId, request.accountType) ?: throw IllegalStateException("Client not found")
        val pixKey = request.toModel(bankAccountResponse.toModel())
        keyRepository.save(pixKey)
        bcb.createPixKey(pixKey.toCreatePixKeyRequest())
        return pixKey
    }
}