package br.com.zup.edu.pix.find.service

import br.com.zup.edu.integration.BCB
import br.com.zup.edu.pix.delete.exception.PixKeyNotFoundException
import br.com.zup.edu.pix.repository.PixKeyRepository
import br.com.zup.edu.shared.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

sealed class PixKeyFinder {

    abstract fun find(bcb: BCB, repository: PixKeyRepository): PixKeyDetails

    @Introspected
    data class FindPixKeyByPixIdAndClientId(
        @field:NotBlank
        @field:ValidUUID
        val clientId: String?,

        @field:NotBlank
        @field:ValidUUID
        val pixId: String?,
    ) : PixKeyFinder() {

        override fun find(bcb: BCB, repository: PixKeyRepository): PixKeyDetails {
            return repository.findByUuid(UUID.fromString(pixId))
                .map { pixKey -> pixKey.toPixKeyDetails() }
                .orElseThrow { PixKeyNotFoundException() }
        }

    }

    @Introspected
    data class FindPixKeyByValue(
        @field:NotBlank
        @field:Size(max = 77)
        val keyValue: String?
    ) : PixKeyFinder() {

        override fun find(bcb: BCB, repository: PixKeyRepository): PixKeyDetails {
            requireNotNull(keyValue) { "KeyValue must not be null" }
            return repository.findByKeyValue(keyValue)
                .map { pixKey -> pixKey.toPixKeyDetails() }
                .orElseGet {
                    Optional.ofNullable(bcb.findPixKey(keyValue))
                        .map { bcbPixDetailsResponse -> bcbPixDetailsResponse.toPixKeyDetails() }
                        .orElseThrow { PixKeyNotFoundException() }
                }
        }

    }

}
