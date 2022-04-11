package com.norberttalpos.customer.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.customer.api.dto.AddressDto
import com.norberttalpos.customer.api.dto.CustomerDto
import com.norberttalpos.customer.api.filter.CustomerFilter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer")
interface CustomerController : AbstractDeletableController<CustomerDto, CustomerFilter> {

    @PutMapping("/addAddressInfo")
    fun addAddressInfo(@RequestBody addressInfo: AddressDto)
}