package br.com.zup.edu.pix.find.service

import br.com.zup.edu.pix.PixKey
import br.com.zup.edu.pix.delete.exception.PixKeyNotFoundException
import br.com.zup.edu.pix.find.service.request.FindPixKeyRequest
import br.com.zup.edu.pix.repository.PixKeyRepository
import javax.inject.Singleton

@Singleton
class FindPixKeyService(
    val repository: PixKeyRepository
) {
    fun findKey(request: FindPixKeyRequest): PixKey {
        val pixKey = repository.findByUuid(request.pixId).orElseThrow { PixKeyNotFoundException() }
        if (!pixKey.belongsTo(request.clientId)) throw IllegalArgumentException("Pix key does not belong to this clientId")
        return pixKey
    }

}
