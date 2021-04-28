package br.com.zup.edu.pix.delete.service.request

import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank

@Introspected
data class RemovePixKeyRequest(
    @field:NotBlank
    val clientId: UUID,

    @field:NotBlank
    val pixId: UUID
)
