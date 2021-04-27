package br.com.zup.shared.validation

import br.com.zup.edu.pix.registry.service.request.RegisterPixKeyRequest
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class ValidPixKeyValidator: ConstraintValidator<ValidPixKey, RegisterPixKeyRequest> {
    private val logger = LoggerFactory.getLogger(ValidPixKeyValidator::class.java);

    override fun isValid(
        value: RegisterPixKeyRequest?,
        annotationMetadata: AnnotationValue<ValidPixKey>,
        context: ConstraintValidatorContext
    ): Boolean {
        logger.info("Running ValidPixKeyValidator...")
        if(value == null) return true
        return value.isKeyValueValid()
    }
}