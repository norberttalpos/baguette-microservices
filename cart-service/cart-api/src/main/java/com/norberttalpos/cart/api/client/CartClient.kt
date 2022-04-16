package com.norberttalpos.cart.api.client

import com.norberttalpos.cart.api.controller.payload.CreateCartRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@FeignClient(
    value = "cart",
    url = "http://localhost:8080"
)
interface CartClient {

    @PostMapping("/api/cart/cart")
    fun createCart(@RequestBody createCartRequest: CreateCartRequest): ResponseEntity<UUID>
}