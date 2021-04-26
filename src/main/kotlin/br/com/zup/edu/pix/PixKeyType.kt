package br.com.zup.edu.pix

import br.com.zup.edu.pix.registry.service.request.CreatePixKeyType

enum class PixKeyType(val createPixKeyType: CreatePixKeyType) {
    CPF(CreatePixKeyType.CPF),
    CNPJ(CreatePixKeyType.CNPJ),
    PHONE(CreatePixKeyType.PHONE),
    EMAIL(CreatePixKeyType.EMAIL),
    RANDOM(CreatePixKeyType.RANDOM)
}
