package br.com.zup.edu.pix.registry.exception

import br.com.zup.edu.shared.exception.ExceptionHandler
import io.grpc.Status
import io.grpc.StatusRuntimeException
import javax.inject.Singleton

@Singleton
class PixKeyAlreadyExistsExceptionHandler : ExceptionHandler<PixKeyAlreadyExistsException> {

    override fun handle(exception: PixKeyAlreadyExistsException): StatusRuntimeException {
        return Status.ALREADY_EXISTS.withDescription(exception.message).withCause(exception).asRuntimeException()
    }

    override fun canHandle(exception: Exception): Boolean {
        return exception is PixKeyAlreadyExistsException
    }
}