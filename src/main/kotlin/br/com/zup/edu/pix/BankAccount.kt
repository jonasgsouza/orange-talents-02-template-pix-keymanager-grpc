package br.com.zup.edu.pix

import javax.persistence.*

@Entity
class BankAccount(
    @field:Enumerated(EnumType.STRING)
    val type: BankAccountType,

    val agency: String,
    val number: String,

    @field:ManyToOne(cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    val holder: Holder,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null;
}