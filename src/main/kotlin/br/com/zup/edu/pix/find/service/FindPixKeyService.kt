package br.com.zup.edu.pix.find.service

import br.com.zup.edu.integration.BCB
import br.com.zup.edu.pix.repository.PixKeyRepository
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.validation.Valid

@Singleton
@Validated
class FindPixKeyService(
    val repository: PixKeyRepository,
    val bcb: BCB
) {

    fun findKey(@Valid finder: PixKeyFinder): PixKeyDetails {
        return finder.find(bcb, repository)
    }

}
