package br.com.zup.edu.shared.exception

import com.google.protobuf.Any
import com.google.rpc.BadRequest
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.protobuf.StatusProto
import javax.inject.Singleton
import javax.validation.ConstraintViolationException
import com.google.rpc.Status as StatusGoogle

@Singleton
class ConstraintViolationExceptionHandler : ExceptionHandler<ConstraintViolationException> {

    override fun handle(exception: ConstraintViolationException): StatusRuntimeException {
        val status: StatusGoogle = StatusGoogle.newBuilder().run {
            code = Status.Code.FAILED_PRECONDITION.value()
            message = "Invalid Arguments"
            val details: List<BadRequest.FieldViolation> = exception.constraintViolations.map { violation ->
                BadRequest.FieldViolation.newBuilder()
                    .setField(violation.propertyPath.toString())
                    .setDescription(violation.message)
                    .build()

            }
            addDetails(Any.pack(BadRequest.newBuilder().addAllFieldViolations(details).build()))
            build()
        }
        return StatusProto.toStatusRuntimeException(status)
    }

    override fun canHandle(exception: Exception): Boolean {
        return exception is ConstraintViolationException
    }
}