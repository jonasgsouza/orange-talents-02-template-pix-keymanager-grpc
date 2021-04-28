package br.com.zup.edu.pix

import br.com.zup.edu.integration.request.OwnerRequest
import javax.persistence.Embeddable

@Embeddable
class Holder(
    val name: String,
    val document: String
) {
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    val id: Long? = null

    fun toOwnerRequest(): OwnerRequest {
        return OwnerRequest(
            type = getOwnerType(),
            name = name,
            taxIdNumber = document
        )
    }

    private fun getOwnerType(): OwnerRequest.OwnerType {
        return when (document.length) {
            11 -> OwnerRequest.OwnerType.NATURAL_PERSON
            14 -> OwnerRequest.OwnerType.LEGAL_PERSON
            else -> throw IllegalArgumentException("O documento do titular possui tamanho inválido")
        }
    }
}
