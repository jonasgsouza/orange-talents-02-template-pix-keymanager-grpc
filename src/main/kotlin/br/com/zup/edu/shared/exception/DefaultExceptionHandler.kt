package br.com.zup.edu.shared.exception

import io.grpc.Status
import io.grpc.StatusRuntimeException

class DefaultExceptionHandler : ExceptionHandler<Exception> {

    override fun handle(exception: Exception): StatusRuntimeException {
        val status = if (exception is IllegalArgumentException) Status.INVALID_ARGUMENT else Status.UNKNOWN
        return status.withDescription(exception.message).withCause(exception).asRuntimeException()
    }

    override fun canHandle(exception: Exception): Boolean {
        return true
    }

}