package poc.renanpelicari.accounting.application.exceptions

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionHandler {

    val log: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handlerHttpRequestMethodNotSupportedException(ex: Exception) = let {

        val error = ResponseError(
            HttpStatus.METHOD_NOT_ALLOWED.value(),
            HttpStatus.METHOD_NOT_ALLOWED.name,
            ex.message.orEmpty(),
            LocalDateTime.now()
        )

        log.error("Error:  $error")

        ResponseEntity(error, HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handlerResourceNotFoundException(ex: ResourceNotFoundException) = let {

        val error = ResponseError(
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.name,
            ex.message.orEmpty(),
            LocalDateTime.now()
        )

        log.error("Error:  $error")

        ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException) = let {

        val bindingResultErrors = ex.bindingResult.fieldErrors
        val errors = ResponseError(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.name,
            mutableListOf(),
            LocalDateTime.now()
        )

        bindingResultErrors.map {
            "Field ${it.field}: ${it.defaultMessage.orEmpty()}"
        }.sorted().forEach { errors.add(it) }

        log.error("Error:  $errors")

        ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}
