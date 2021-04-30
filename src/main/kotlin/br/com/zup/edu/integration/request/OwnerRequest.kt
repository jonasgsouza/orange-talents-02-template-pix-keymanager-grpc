package br.com.zup.edu.integration.request

import br.com.zup.edu.integration.enums.OwnerType
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
                type = when (holder.document.length) {
                    11 -> OwnerType.NATURAL_PERSON
                    14 -> OwnerType.LEGAL_PERSON
                    else -> throw IllegalArgumentException("O documento do titular possui tamanho inválido")
                },
                name = holder.name,
                taxIdNumber = holder.document
            )
        }
    }

}
