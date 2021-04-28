package br.com.zup.edu.pix.delete.service

import br.com.zup.edu.integration.BCB
import br.com.zup.edu.integration.request.DeletePixKeyRequest
import br.com.zup.edu.pix.delete.exception.PixKeyNotFoundException
import br.com.zup.edu.pix.delete.service.request.RemovePixKeyRequest
import br.com.zup.edu.pix.repository.PixKeyRepository
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
@Validated
class RemovePixKeyService(
    val repository: PixKeyRepository,
    val bcb: BCB
) {

    @Transactional
    fun removeKey(@Valid request: RemovePixKeyRequest) {
        val possiblePixKey = repository.findByUuid(request.pixId)
        if (possiblePixKey.isEmpty) throw PixKeyNotFoundException()
        val pixKey = possiblePixKey.get()
        if (!pixKey.belongsTo(request.clientId)) throw IllegalArgumentException("Pix key does not belong to this clientId")
        repository.deleteById(pixKey.id!!)
        val deleteRequest = DeletePixKeyRequest.of(pixKey)
        bcb.deletePixKey(deleteRequest.key, deleteRequest)
    }
}