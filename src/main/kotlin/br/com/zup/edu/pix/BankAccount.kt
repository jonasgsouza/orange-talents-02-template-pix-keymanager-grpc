package br.com.zup.edu.pix

import br.com.zup.edu.pix.enums.BankAccountType
import br.com.zup.edu.pix.find.service.BankAccountDetails
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

@Embeddable
class BankAccount(
    @Enumerated(STRING)
    val accountType: BankAccountType,

    val institutionName: String,

    val ispb: String,

    val agency: String,

    val number: String,

//    @field:ManyToOne(cascade = [MERGE, PERSIST])
    @Embedded
    val holder: Holder,
) {
    fun toBankAccountDetails(): BankAccountDetails {
        return BankAccountDetails(
            institutionName = institutionName,
            agency = agency,
            number = number,
            accountType = accountType,
            holder = holder.toHolderDetails()
        )
    }

//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    val id: Long? = null;
}