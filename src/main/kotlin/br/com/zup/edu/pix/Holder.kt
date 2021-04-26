package br.com.zup.edu.pix

import br.com.zup.edu.pix.registry.service.request.OwnerRequest
import br.com.zup.edu.pix.registry.service.request.OwnerType
import java.lang.IllegalArgumentException
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Holder(
    val name: String,
    val document: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun toOwnerRequest(): OwnerRequest {
        return OwnerRequest(
            type = getOwnerType(),
            name = name,
            taxIdNumber = document
        )
    }

    private fun getOwnerType(): OwnerType {
        return when (document.length) {
            11 -> OwnerType.NATURAL_PERSON
            14 -> OwnerType.LEGAL_PERSON
            else -> throw IllegalArgumentException("O documento do titular possui tamanho inválido")
        }
    }
}
