package com.norberttalpos.common.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.persistence.EntityNotFoundException

class NotValidUpdateException(override val message: String? = "Not valid update") : Exception(message)

@ExceptionHandler(EntityNotFoundException::class)
fun handleEntityNotFoundException(): ResponseEntity<Any> {
    return ResponseEntity.notFound().build()
}

@ExceptionHandler(NotValidUpdateException::class)
fun handleNotValidUpdateException(e: NotValidUpdateException): ResponseEntity<Any> {
    return ResponseEntity.badRequest().body(e.message)
}

@ExceptionHandler(NullPointerException::class)
fun handleNullPointerException(): ResponseEntity<Any> {
    return ResponseEntity.internalServerError().body("Internal server error occured")
}


@ExceptionHandler(IllegalArgumentException::class)
fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<Any> {
    return ResponseEntity.badRequest().body(e.message)
}