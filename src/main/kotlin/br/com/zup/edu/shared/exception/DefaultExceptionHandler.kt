package br.com.zup.edu.shared.exception

import io.grpc.Status
import java.lang.IllegalArgumentException

class DefaultExceptionHandler: ExceptionHandler<Exception> {

    override fun handle(exception: Exception): Status {
        val status = if(exception is IllegalArgumentException) Status.INVALID_ARGUMENT else Status.UNKNOWN
        return status.withDescription(exception.message).withCause(exception)
    }

    override fun canHandle(exception: Exception): Boolean {
        return true
    }
}