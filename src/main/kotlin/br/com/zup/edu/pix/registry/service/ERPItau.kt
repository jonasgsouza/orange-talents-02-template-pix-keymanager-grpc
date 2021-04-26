package br.com.zup.edu.pix.registry.service

import br.com.zup.edu.pix.BankAccountType
import br.com.zup.edu.pix.registry.service.response.BankAccountQueryResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:9091/api/v1/clientes")
interface ERPItau {

    @Get("/{clientId}/contas")
    fun findBankAccount(
        @PathVariable clientId: String,
        @QueryValue("tipo") accountType: BankAccountType
    ): BankAccountQueryResponse
}