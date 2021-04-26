package br.com.zup.edu.pix

import br.com.zup.edu.pix.registry.service.request.CreatePixKeyRequest

enum class PixKeyType(val bcbPixKeyType: CreatePixKeyRequest.PixKeyType) {
    CPF(CreatePixKeyRequest.PixKeyType.CPF),
    CNPJ(CreatePixKeyRequest.PixKeyType.CNPJ),
    PHONE(CreatePixKeyRequest.PixKeyType.PHONE),
    EMAIL(CreatePixKeyRequest.PixKeyType.EMAIL),
    RANDOM(CreatePixKeyRequest.PixKeyType.RANDOM)
}
