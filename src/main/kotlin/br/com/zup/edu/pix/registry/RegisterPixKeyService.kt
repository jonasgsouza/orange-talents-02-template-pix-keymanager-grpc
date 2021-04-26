package br.com.zup.edu.pix.registry

import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.PixKeyRepository
import br.com.zup.edu.pix.registry.service.BCB
import br.com.zup.edu.pix.registry.service.ERPItau
import br.com.zup.edu.pix.registry.service.response.BankAccountQueryResponse
import io.micronaut.validation.Validated
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
        val bankAccountResponse: BankAccountQueryResponse = erpItau.findBankAccount(request.clientId, request.accountType)
        val pixKey = request.toModel(bankAccountResponse.toModel())
        keyRepository.save(pixKey)
        bcb.createPixKey(pixKey.toCreatePixKeyRequest())
        return pixKey
    }
}