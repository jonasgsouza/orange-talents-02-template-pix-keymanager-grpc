package br.com.zup.edu.pix

import br.com.zup.integration.request.CreatePixKeyRequest
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
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

    val uuid: UUID = UUID.randomUUID()

    val createdAt: LocalDateTime = LocalDateTime.now()

    fun toCreatePixKeyRequest(): CreatePixKeyRequest {
        return CreatePixKeyRequest(
            keyType = keyType.bcbPixKeyType,
            key = keyValue,
            bankAccount = account.toBankAccountRequest(),
            owner = account.holder.toOwnerRequest()
        )
    }

    enum class PixKeyType(val bcbPixKeyType: CreatePixKeyRequest.PixKeyType) {
        CPF(CreatePixKeyRequest.PixKeyType.CPF),
        CNPJ(CreatePixKeyRequest.PixKeyType.CNPJ),
        PHONE(CreatePixKeyRequest.PixKeyType.PHONE),
        EMAIL(CreatePixKeyRequest.PixKeyType.EMAIL),
        RANDOM(CreatePixKeyRequest.PixKeyType.RANDOM)
    }
}