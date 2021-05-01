package br.com.zup.edu.pix.repository

import br.com.zup.edu.pix.PixKey
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface PixKeyRepository : JpaRepository<PixKey, Long> {
    fun existsByKeyValue(keyValue: String?): Boolean

    fun findByUuid(uuid: UUID): Optional<PixKey>

    fun findByKeyValue(keyValue: String): Optional<PixKey>
}