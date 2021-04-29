package br.com.zup.edu.pix

import br.com.zup.edu.integration.request.BankAccountRequest
import javax.persistence.*
import javax.persistence.EnumType.STRING

@Embeddable
class BankAccount(
    @field:Enumerated(STRING)
    val accountType: BankAccountType,

    val institutionName: String,

    val ispb: String,

    val agency: String,

    val number: String,

//    @field:ManyToOne(cascade = [MERGE, PERSIST])
    @field:Embedded
    val holder: Holder,
) {

//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    val id: Long? = null;

    enum class BankAccountType(val bankAccountRequestType: BankAccountRequest.BankAccountType) {
        CONTA_CORRENTE(BankAccountRequest.BankAccountType.CACC),
        CONTA_POUPANCA(BankAccountRequest.BankAccountType.SVGS)
    }
}