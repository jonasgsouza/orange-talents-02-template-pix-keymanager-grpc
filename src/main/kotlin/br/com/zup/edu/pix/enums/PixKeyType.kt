package br.com.zup.edu.pix.enums

import br.com.zup.edu.KeyTypeGrpc
import br.com.zup.edu.integration.enums.BCBPixKeyType

enum class PixKeyType(val bcbPixKeyType: BCBPixKeyType, val grpcPixKeyType: KeyTypeGrpc) {
    CPF(BCBPixKeyType.CPF, KeyTypeGrpc.CPF),
    CNPJ(BCBPixKeyType.CNPJ, KeyTypeGrpc.CNPJ),
    PHONE(BCBPixKeyType.PHONE, KeyTypeGrpc.PHONE),
    EMAIL(BCBPixKeyType.EMAIL, KeyTypeGrpc.EMAIL),
    RANDOM(BCBPixKeyType.RANDOM, KeyTypeGrpc.RANDOM)
}