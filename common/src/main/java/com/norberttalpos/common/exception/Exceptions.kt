package com.norberttalpos.common.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.persistence.EntityNotFoundException

class NotValidUpdateException(override val message: String? = "Not valid update") : Exception(message)

@ExceptionHandler(EntityNotFoundException::class)
fun handleException(e: EntityNotFoundException): ResponseEntity<Any> {
    return ResponseEntity.notFound().build()
}

@ExceptionHandler(NotValidUpdateException::class)
fun handleException(e: NotValidUpdateException): ResponseEntity<Any> {
    return ResponseEntity.badRequest().body(e.message)
}