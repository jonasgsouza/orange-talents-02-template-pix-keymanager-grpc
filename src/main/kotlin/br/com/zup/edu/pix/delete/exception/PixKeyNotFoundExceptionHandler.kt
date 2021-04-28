package br.com.zup.edu.pix.delete.exception

import br.com.zup.edu.shared.exception.ExceptionHandler
import io.grpc.Status
import javax.inject.Singleton

@Singleton
class PixKeyNotFoundExceptionHandler : ExceptionHandler<PixKeyNotFoundException> {

    override fun handle(exception: PixKeyNotFoundException): Status {
        return Status.NOT_FOUND.withDescription(exception.message).withCause(exception)
    }

    override fun canHandle(exception: Exception): Boolean {
        return exception is PixKeyNotFoundException
    }
}