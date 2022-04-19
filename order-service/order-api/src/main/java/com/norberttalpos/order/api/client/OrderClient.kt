package com.norberttalpos.order.api.client

import com.norberttalpos.common.feign.FeignConfiguration
import com.norberttalpos.order.api.dto.OrderDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import java.util.*

@FeignClient(
    configuration = [FeignConfiguration::class],
    value = "order",
    url = "http://localhost:8080"
)
interface OrderClient {

    @PostMapping("/api/order/order/")
    fun createOrder(order: OrderDto, @RequestHeader("Authorization") token: String)

    @DeleteMapping("/api/order/order/delete-customer-orders")
    fun deleteCustomerOrders(@RequestHeader("Authorization") token: String): ResponseEntity<Long>
}