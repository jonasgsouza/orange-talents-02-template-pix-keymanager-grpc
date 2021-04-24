package br.com.zup.edu.pix

import br.com.zup.edu.pix.PixKey
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface PixKeyRepository: JpaRepository<PixKey, Long>