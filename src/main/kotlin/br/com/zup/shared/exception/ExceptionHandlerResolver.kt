package br.com.zup.shared.exception

import javax.inject.Singleton

@Singleton
class ExceptionHandlerResolver(
    private val handlers: List<ExceptionHandler<in Exception>>,
) {

    private val defaultHandler: DefaultExceptionHandler = DefaultExceptionHandler()

    fun resolve(exception: Exception): ExceptionHandler<in Exception> {
        val foundHandlers = handlers.filter { handler -> handler.canHandle(exception) }
        if (foundHandlers.size > 1) error(
            "Found more than 1 handler for exception ${exception.javaClass.simpleName}. Handlers: ${
                foundHandlers.toTypedArray().contentToString()
            }"
        )
        return foundHandlers.firstOrNull() ?: defaultHandler
    }

}