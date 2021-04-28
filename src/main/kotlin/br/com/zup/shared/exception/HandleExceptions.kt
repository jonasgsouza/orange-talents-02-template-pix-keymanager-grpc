package br.com.zup.shared.exception

import br.com.zup.shared.exception.ExceptionInterceptor
import io.micronaut.aop.Around
import io.micronaut.context.annotation.Type
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.TYPE

@Around
@Type(ExceptionInterceptor::class)
@Target(CLASS, TYPE)
@Retention(RUNTIME)
annotation class HandleExceptions()
