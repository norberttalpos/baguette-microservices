package com.norberttalpos.cart.api.client

import com.norberttalpos.cart.api.controller.payload.CreateCartRequest
import com.norberttalpos.common.feign.FeignConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import java.util.*

@FeignClient(
    configuration = [FeignConfiguration::class],
    value = "cart",
    url = "http://localhost:8080"
)
interface CartClient {

    @PostMapping("/api/cart/cart/")
    fun createCart(
        @RequestBody createCartRequest: CreateCartRequest,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<Long>

    @DeleteMapping("/api/cart/cart/delete-customer-carts")
    fun deleteCustomerCarts(@RequestHeader("Authorization") token: String): ResponseEntity<Long>
}