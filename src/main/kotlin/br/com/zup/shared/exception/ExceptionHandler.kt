package br.com.zup.shared.exception

import io.grpc.Status

interface ExceptionHandler<T : Exception> {

    fun handle(exception: T): Status

    fun canHandle(exception: Exception): Boolean
}