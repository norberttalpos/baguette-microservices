package com.norberttalpos.order.api.client

import com.norberttalpos.order.api.dto.OrderDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    value = "order",
    url = "http://localhost:8080"
)
interface OrderClient {

    @PostMapping("/api/order/order/")
    fun createOrder(order: OrderDto, @RequestHeader("Authorization") token: String)
}