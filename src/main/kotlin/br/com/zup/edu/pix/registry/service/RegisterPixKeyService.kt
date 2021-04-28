package br.com.zup.edu.pix.registry.service

import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.registry.service.request.RegisterPixKeyRequest
import br.com.zup.edu.pix.repository.PixKeyRepository
import br.com.zup.edu.pix.registry.exception.PixKeyAlreadyExistsException
import br.com.zup.integration.BCB
import br.com.zup.integration.ERPItau
import br.com.zup.integration.response.BankAccountQueryResponse
import io.micronaut.validation.Validated
import java.lang.IllegalArgumentException
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
        if (keyRepository.existsByKeyValue(request.keyValue)) throw PixKeyAlreadyExistsException()
        val bankAccountResponse: BankAccountQueryResponse =
            erpItau.findBankAccount(request.clientId.toString(), request.accountType)
                ?: throw IllegalArgumentException("Client not found")
        val pixKey = request.toModel(bankAccountResponse.toModel())
        keyRepository.save(pixKey)
        bcb.createPixKey(pixKey.toCreatePixKeyRequest())
        return pixKey
    }
}