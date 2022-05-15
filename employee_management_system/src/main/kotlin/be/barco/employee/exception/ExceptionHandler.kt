package be.barco.employee.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun generalExceptionHandler(exception: Exception): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(exception.message)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(CustomException::class)
    fun customExceptionHandler(exception: Exception): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(exception.message)
        return ResponseEntity(error, error.status)
    }
}