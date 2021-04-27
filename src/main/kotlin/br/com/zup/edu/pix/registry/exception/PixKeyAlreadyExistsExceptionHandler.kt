package br.com.zup.edu.pix.registry.exception

import br.com.zup.edu.shared.exception.ExceptionHandler
import io.grpc.Status
import javax.inject.Singleton

@Singleton
class PixKeyAlreadyExistsExceptionHandler : ExceptionHandler<PixKeyAlreadyExistsException> {

    override fun handle(exception: PixKeyAlreadyExistsException): Status {
        return Status.ALREADY_EXISTS.withDescription(exception.message).withCause(exception)
    }

    override fun canHandle(exception: Exception): Boolean {
        return exception is PixKeyAlreadyExistsException
    }
}