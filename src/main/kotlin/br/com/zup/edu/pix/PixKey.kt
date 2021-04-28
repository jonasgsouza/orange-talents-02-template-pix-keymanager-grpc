package br.com.zup.edu.pix

import br.com.zup.edu.integration.request.CreatePixKeyRequest
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class PixKey(
    @field:NotBlank
    val clientId: UUID,

    @field:NotNull
    @Enumerated(STRING)
    @Column(nullable = false)
    val keyType: PixKeyType,

    @field:NotBlank
    @Column(unique = true, nullable = false, length = 77)
    val keyValue: String?,

//    @field:ManyToOne(cascade = [MERGE, PERSIST])
    @field:NotNull
    @Embedded
    @Column(nullable = false)
    val account: BankAccount
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null

    @Column(unique = true, nullable = false)
    val uuid: UUID = UUID.randomUUID()

    val createdAt: LocalDateTime = LocalDateTime.now()

    fun belongsTo(clientId: UUID): Boolean {
        return this.clientId == clientId
    }

    enum class PixKeyType(val bcbPixKeyType: CreatePixKeyRequest.PixKeyType) {
        CPF(CreatePixKeyRequest.PixKeyType.CPF),
        CNPJ(CreatePixKeyRequest.PixKeyType.CNPJ),
        PHONE(CreatePixKeyRequest.PixKeyType.PHONE),
        EMAIL(CreatePixKeyRequest.PixKeyType.EMAIL),
        RANDOM(CreatePixKeyRequest.PixKeyType.RANDOM)
    }
}