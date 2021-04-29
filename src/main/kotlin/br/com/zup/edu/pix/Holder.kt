package br.com.zup.edu.pix

import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Holder(
    @Column(name = "clientId")
    val id: UUID,
    val name: String,
    val document: String
) {
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    val id: Long? = null


}
