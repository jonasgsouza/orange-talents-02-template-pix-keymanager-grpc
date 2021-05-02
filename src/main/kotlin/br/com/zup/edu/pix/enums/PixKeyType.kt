package br.com.zup.edu.pix.enums

import br.com.zup.edu.KeyTypeGrpc
import br.com.zup.edu.integration.enums.BCBPixKeyType

enum class PixKeyType() {
    CPF,
    CNPJ,
    PHONE,
    EMAIL,
    RANDOM;

    fun toBCBPixKeyType(): BCBPixKeyType {
        return when (this) {
            CPF -> BCBPixKeyType.CPF
            CNPJ -> BCBPixKeyType.CNPJ
            PHONE -> BCBPixKeyType.PHONE
            EMAIL -> BCBPixKeyType.EMAIL
            RANDOM -> BCBPixKeyType.RANDOM
        }
    }

    fun toKeyTypeGrpc(): KeyTypeGrpc {
        return when (this) {
            CPF -> KeyTypeGrpc.CPF
            CNPJ -> KeyTypeGrpc.CNPJ
            PHONE -> KeyTypeGrpc.PHONE
            EMAIL -> KeyTypeGrpc.EMAIL
            RANDOM -> KeyTypeGrpc.RANDOM
        }
    }
}