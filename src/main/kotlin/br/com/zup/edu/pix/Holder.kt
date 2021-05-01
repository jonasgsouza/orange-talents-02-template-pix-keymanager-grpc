package br.com.zup.edu.pix

import br.com.zup.edu.pix.find.service.HolderDetails
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
    fun toHolderDetails(): HolderDetails {
        return HolderDetails(
            id = id,
            name = name,
            document = document
        )
    }
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    val id: Long? = null


}
