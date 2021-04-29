package br.com.zup.edu.integration.request

import br.com.zup.edu.pix.Holder
import io.micronaut.core.annotation.Introspected

@Introspected
data class OwnerRequest(
    val type: OwnerType,
    val name: String,
    val taxIdNumber: String
) {

    companion object {
        fun from(holder: Holder): OwnerRequest {
            return OwnerRequest(
                type = getOwnerType(holder.document),
                name = holder.name,
                taxIdNumber = holder.document
            )
        }

        private fun getOwnerType(document: String): OwnerType {
            return when (document.length) {
                11 -> OwnerRequest.OwnerType.NATURAL_PERSON
                14 -> OwnerRequest.OwnerType.LEGAL_PERSON
                else -> throw IllegalArgumentException("O documento do titular possui tamanho inválido")
            }
        }
    }


    enum class OwnerType {
        NATURAL_PERSON, LEGAL_PERSON
    }
}
