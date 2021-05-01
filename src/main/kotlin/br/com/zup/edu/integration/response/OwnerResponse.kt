package br.com.zup.edu.integration.response

import br.com.zup.edu.integration.enums.OwnerType
import br.com.zup.edu.pix.find.service.HolderDetails
import io.micronaut.core.annotation.Introspected

@Introspected
data class OwnerResponse(
    val type: OwnerType,
    val name: String,
    val taxIdNumber: String
) {
    fun toHolderDetails(): HolderDetails {
        return HolderDetails(
            name = name,
            document = taxIdNumber
        )
    }
}
