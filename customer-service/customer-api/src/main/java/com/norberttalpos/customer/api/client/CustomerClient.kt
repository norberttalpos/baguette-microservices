package com.norberttalpos.customer.api.client

import com.norberttalpos.customer.api.dto.CustomerDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    value = "customer",
    url = "http://localhost:8084"
)
interface CustomerClient {

    @PostMapping("/api/customer/customer/")
    fun registerCustomer(customer: CustomerDto)
}