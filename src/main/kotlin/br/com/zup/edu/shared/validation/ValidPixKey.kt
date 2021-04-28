package br.com.zup.edu.shared.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.TYPE
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [ValidPixKeyValidator::class])
@Retention(RUNTIME)
@Target(CLASS, TYPE)
annotation class ValidPixKey(
    val message: String = "not a valid pix key",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Payload>> = []
)
