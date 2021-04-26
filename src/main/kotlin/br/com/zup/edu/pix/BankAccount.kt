package br.com.zup.edu.pix

import br.com.zup.edu.pix.registry.service.request.BankAccountRequest
import javax.persistence.*

@Entity
class BankAccount(
    @field:Enumerated(EnumType.STRING)
    val bankAccountType: BankAccountType,

    val ispb: String,

    val agency: String,

    val number: String,

    @field:ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    val holder: Holder,
    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null;

    fun toBankAccountRequest(): BankAccountRequest {
        return BankAccountRequest(
            participant = ispb,
            branch = agency,
            accountNumber = number,
            accountType = bankAccountType.bankAccountRequestType
        )
    }

    enum class BankAccountType(val bankAccountRequestType: BankAccountRequest.BankAccountType) {
        CONTA_CORRENTE(BankAccountRequest.BankAccountType.CACC),
        CONTA_POUPANCA(BankAccountRequest.BankAccountType.SVGS)
    }
}