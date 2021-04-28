package br.com.zup.edu.integration

import br.com.zup.edu.integration.request.CreatePixKeyRequest
import br.com.zup.edu.integration.request.DeletePixKeyRequest
import io.micronaut.http.MediaType.APPLICATION_XML
import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:8082/api/v1/pix/keys")
interface BCB {

    @Post
    @Consumes(APPLICATION_XML)
    @Produces(APPLICATION_XML)
    fun createPixKey(@Body createPixRequest: CreatePixKeyRequest): Any?

    @Delete("{key}")
    @Consumes(APPLICATION_XML)
    @Produces(APPLICATION_XML)
    fun deletePixKey(@PathVariable key: String, @Body request: DeletePixKeyRequest): Any?
}