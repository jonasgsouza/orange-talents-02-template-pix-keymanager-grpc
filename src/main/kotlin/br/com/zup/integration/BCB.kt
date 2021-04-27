package br.com.zup.integration

import br.com.zup.integration.request.CreatePixKeyRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType.APPLICATION_XML
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("http://localhost:8082/api/v1/pix/keys")
interface BCB {

    @Post(consumes = [APPLICATION_XML], produces = [APPLICATION_XML])
    fun createPixKey(@Body createPixKeyRequest: CreatePixKeyRequest): HttpResponse<Any>
}