package be.barco.employee.exception

import be.barco.employee.utils.Constants
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
    private val msg: String?,
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
    val code: Int = status.value(),
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    val message: String
        get() = msg ?: Constants.SOMETHING_WENT_WRONG
}
