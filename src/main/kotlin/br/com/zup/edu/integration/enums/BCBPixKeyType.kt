package br.com.zup.edu.integration.enums

import br.com.zup.edu.pix.enums.PixKeyType
import io.micronaut.core.annotation.Introspected

@Introspected
enum class BCBPixKeyType {
    CPF,
    CNPJ,
    PHONE,
    EMAIL,
    RANDOM;

    fun toPixKeyType(): PixKeyType {
        return when (this) {
            CPF -> PixKeyType.CPF
            CNPJ -> PixKeyType.CNPJ
            PHONE -> PixKeyType.PHONE
            EMAIL -> PixKeyType.EMAIL
            RANDOM -> PixKeyType.RANDOM
        }
    }
}