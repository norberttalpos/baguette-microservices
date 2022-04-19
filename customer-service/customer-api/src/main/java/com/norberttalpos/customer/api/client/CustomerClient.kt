package com.norberttalpos.customer.api.client

import com.norberttalpos.common.feign.FeignConfiguration
import com.norberttalpos.customer.api.dto.CustomerDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@FeignClient(
    configuration = [FeignConfiguration::class],
    value = "customer",
    url = "http://localhost:8080"
)
interface CustomerClient {

    @PostMapping("/api/customer/customer/")
    fun registerCustomer(customer: CustomerDto, @RequestHeader("Authorization") token: String)

    @PutMapping("/api/customer/customer/")
    fun updateCustomer(customer: CustomerDto, @RequestHeader("Authorization") token: String)

    @GetMapping("/api/customer/customer/{id}/userExistsById")
    fun userExistsById(
        @PathVariable id: Long,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<Boolean>
}