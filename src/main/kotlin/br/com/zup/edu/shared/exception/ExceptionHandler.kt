package br.com.zup.edu.shared.exception

import io.grpc.StatusRuntimeException

interface ExceptionHandler<T : Exception> {

    fun handle(exception: T): StatusRuntimeException

    fun canHandle(exception: Exception): Boolean
}