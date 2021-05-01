package br.com.zup.edu.pix.delete.service.request

import br.com.zup.edu.shared.validation.ValidUUID
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class RemovePixKeyRequest(
    @field:NotBlank
    @ValidUUID
    val clientId: String?,

    @field:NotBlank
    @ValidUUID
    val pixId: String?
)
