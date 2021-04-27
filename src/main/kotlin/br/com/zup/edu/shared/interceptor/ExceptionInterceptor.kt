package br.com.zup.edu.shared.interceptor

import io.grpc.Status
import io.grpc.stub.StreamObserver
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ExceptionInterceptor : MethodInterceptor<Any, Any> {
    private val logger = LoggerFactory.getLogger(ExceptionInterceptor::class.java)


    override fun intercept(context: MethodInvocationContext<Any, Any>?): Any? {
        logger.info("Intercepting method ${context?.methodName}")
        return try {
            context?.proceed()
        } catch (e: Exception) {
            logger.info("Catching exception ${e.javaClass.simpleName} thrown in method ${context?.methodName}")
            val possibleResponseObserver = context?.parameterValues?.get(1)
            if (possibleResponseObserver != null && possibleResponseObserver is StreamObserver<*>) {
                val status = Status.UNKNOWN.withDescription(e.message).withCause(e)
                possibleResponseObserver.onError(status.asRuntimeException())
                possibleResponseObserver.onCompleted()
            }
            null
        }
    }

}
