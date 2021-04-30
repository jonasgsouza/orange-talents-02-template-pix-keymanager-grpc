package br.com.zup.edu.pix

import br.com.zup.edu.pix.enums.BankAccountType
import javax.persistence.Embeddable
import javax.persistence.Embedded
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated

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
}