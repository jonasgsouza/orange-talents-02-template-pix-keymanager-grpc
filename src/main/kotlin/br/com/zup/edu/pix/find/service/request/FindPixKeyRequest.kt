package br.com.zup.edu.pix.find.service.request

import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank

@Introspected
data class FindPixKeyRequest(
    @field:NotBlank
    val clientId: UUID,

    @field:NotBlank
    val pixId: UUID
)
