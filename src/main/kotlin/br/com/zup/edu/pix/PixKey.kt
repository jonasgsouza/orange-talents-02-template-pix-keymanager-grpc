package br.com.zup.edu.pix

import br.com.zup.edu.RegisterPixKeyRequest
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class PixKey(
    @field:NotBlank
    val clientId: String,

    @field:NotNull
    @field:Enumerated(EnumType.STRING)
    val keyType: PixKeyType,

    @field:NotBlank
    val keyValue: String,

    @field:NotBlank
    val accountType: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val uuid: UUID = UUID.randomUUID()

    companion object {
        fun from(request: RegisterPixKeyRequest): PixKey {
            return PixKey(
                clientId = request.clientId,
                keyType = PixKeyType.RANDOM_KEY,
                keyValue = request.keyValue,
                accountType = request.accountType.name
            )
        }
    }

}