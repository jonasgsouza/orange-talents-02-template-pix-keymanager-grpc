package br.com.zup.edu.shared.exception

import io.grpc.stub.StreamObserver
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ExceptionInterceptor(
    private val exceptionHandlerResolver: ExceptionHandlerResolver
) : MethodInterceptor<Any, Any> {
    private val logger = LoggerFactory.getLogger(ExceptionInterceptor::class.java)


    override fun intercept(context: MethodInvocationContext<Any, Any>?): Any? {
        logger.info("Intercepting method ${context?.methodName}")
        return try {
            context?.proceed()
        } catch (e: Exception) {
            logger.info("Handling ${e.javaClass.simpleName} thrown in method ${context?.methodName}")
            e.printStackTrace()
            context?.parameterValues?.get(1)?.let { possibleResponseObserver ->
                if (possibleResponseObserver is StreamObserver<*>) {
                    val statusRuntimeException = exceptionHandlerResolver.resolve(e).handle(e)
                    possibleResponseObserver.onError(statusRuntimeException)
                }
            }
            null
        }
    }

}
