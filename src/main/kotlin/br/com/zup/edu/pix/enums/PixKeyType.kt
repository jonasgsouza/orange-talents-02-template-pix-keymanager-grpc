package br.com.zup.edu.pix.enums

import br.com.zup.edu.integration.enums.BCBPixKeyType

enum class PixKeyType(val bcbPixKeyType: BCBPixKeyType) {
    CPF(BCBPixKeyType.CPF),
    CNPJ(BCBPixKeyType.CNPJ),
    PHONE(BCBPixKeyType.PHONE),
    EMAIL(BCBPixKeyType.EMAIL),
    RANDOM(BCBPixKeyType.RANDOM)
}