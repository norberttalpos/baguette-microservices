package com.norberttalpos.customer.api.client

import com.norberttalpos.customer.api.dto.CustomerDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import java.util.*

@FeignClient(
    value = "customer",
    url = "http://localhost:8084"
)
interface CustomerClient {

    @PostMapping("/api/customer/customer/")
    fun registerCustomer(customer: CustomerDto)

    @GetMapping("/api/customer/customer/{id}/userExistsById")
    fun userExistsById(@PathVariable id: UUID): ResponseEntity<Boolean>
}