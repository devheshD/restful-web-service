package com.example.restfulwebservice.exception

import com.example.restfulwebservice.user.UserNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<Any> = ResponseEntity(
        ExceptionResponse(LocalDateTime.now(), ex.message, request.getDescription(false)),
        HttpStatus.INTERNAL_SERVER_ERROR
    )

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundExceptions(ex: Exception, request: WebRequest): ResponseEntity<Any> = ResponseEntity(
        ExceptionResponse(LocalDateTime.now(), ex.message, request.getDescription(false)),
        HttpStatus.NOT_FOUND
    )

    @Override
    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> = ResponseEntity(
        ExceptionResponse(LocalDateTime.now(), "Validation Failed", ex.bindingResult.toString()),
        HttpStatus.BAD_REQUEST
    )
}
