package br.com.zup.edu.shared.validation

import br.com.zup.edu.pix.registry.service.request.RegisterPixKeyRequest
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton

@Singleton
class ValidPixKeyValidator: ConstraintValidator<ValidPixKey, RegisterPixKeyRequest> {

    override fun isValid(
        value: RegisterPixKeyRequest?,
        annotationMetadata: AnnotationValue<ValidPixKey>,
        context: ConstraintValidatorContext
    ): Boolean {
        println("ValidPixKey")
        if(value == null) return true
        return value.isKeyValueValid()
    }
}