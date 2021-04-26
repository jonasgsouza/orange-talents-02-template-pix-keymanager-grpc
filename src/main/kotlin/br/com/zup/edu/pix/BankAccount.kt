package br.com.zup.edu.pix

import br.com.zup.edu.pix.registry.service.request.BankAccountRequest
import javax.persistence.*
import javax.persistence.CascadeType.MERGE
import javax.persistence.CascadeType.PERSIST
import javax.persistence.EnumType.STRING
import javax.persistence.GenerationType.IDENTITY

@Entity
class BankAccount(
    @field:Enumerated(STRING)
    val bankAccountType: BankAccountType,

    val ispb: String,

    val agency: String,

    val number: String,

    @field:ManyToOne(cascade = [MERGE, PERSIST])
    val holder: Holder,
    ) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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