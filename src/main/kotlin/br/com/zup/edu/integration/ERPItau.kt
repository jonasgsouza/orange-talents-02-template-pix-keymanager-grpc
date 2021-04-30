package br.com.zup.edu.integration

import br.com.zup.edu.integration.response.BankAccountQueryResponse
import br.com.zup.edu.pix.enums.BankAccountType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:9091/api/v1/clientes")
interface ERPItau {

    @Get("/{clientId}/contas")
    fun findBankAccount(
        @PathVariable clientId: String,
        @QueryValue("tipo") accountType: BankAccountType?
    ): BankAccountQueryResponse?
}