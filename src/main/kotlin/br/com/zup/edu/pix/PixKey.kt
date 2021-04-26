package br.com.zup.edu.pix

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
    val type: PixKeyType,

    @field:NotBlank
    val value: String,

    @field:ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    val account: BankAccount
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val uuid: UUID = UUID.randomUUID()
}