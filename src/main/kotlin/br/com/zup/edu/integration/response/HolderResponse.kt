package br.com.zup.edu.integration.response

import br.com.zup.edu.pix.Holder
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class HolderResponse(
    val id: UUID,
    @param:JsonProperty("nome") val name: String,
    @param:JsonProperty("cpf") val document: String
) {
    fun toModel(): Holder {
        return Holder(
            name = name,
            document = document
        )
    }
}
