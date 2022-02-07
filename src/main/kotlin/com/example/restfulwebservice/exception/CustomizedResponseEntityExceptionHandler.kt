package com.example.restfulwebservice.exception

import com.example.restfulwebservice.user.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@RestController
@ControllerAdvice
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(
        ex: Exception,
        request: WebRequest,
    ): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
            timestamp = LocalDateTime.now(),
            message = ex.message,
            details = request.getDescription(false)
        )

        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundExceptions(
        ex: Exception,
        request: WebRequest,
    ): ResponseEntity<Any> {
        val exceptionResponse = ExceptionResponse(
            timestamp = LocalDateTime.now(),
            message = ex.message,
            details = request.getDescription(false)
        )

        return ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND)
    }
}
