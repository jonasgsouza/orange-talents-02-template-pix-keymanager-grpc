package br.com.zup.edu.integration.enums

import br.com.zup.edu.pix.enums.PixKeyType
import io.micronaut.core.annotation.Introspected

@Introspected
enum class BCBPixKeyType(val pixKeyType: PixKeyType) {
    CPF(PixKeyType.CPF),
    CNPJ(PixKeyType.CNPJ),
    PHONE(PixKeyType.PHONE),
    EMAIL(PixKeyType.EMAIL),
    RANDOM(PixKeyType.RANDOM)
}