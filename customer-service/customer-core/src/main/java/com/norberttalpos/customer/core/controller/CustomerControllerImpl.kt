package com.norberttalpos.customer.core.controller

import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import com.norberttalpos.customer.api.controller.CustomerController
import com.norberttalpos.customer.api.dto.AddressDto
import com.norberttalpos.customer.api.dto.CustomerDto
import com.norberttalpos.customer.api.filter.CustomerFilter
import com.norberttalpos.customer.core.entity.Customer
import com.norberttalpos.customer.core.mapper.AddressMapper
import com.norberttalpos.customer.core.service.CustomerService
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerControllerImpl(
    private val addressMapper: AddressMapper,
) : CustomerController,
    AbstractDeletableControllerImpl<CustomerDto, Customer, CustomerFilter, CustomerService>() {

    override fun addAddressInfo(addressInfo: AddressDto) {
        this.service.addAddressInfo(addressMapper.fromDto(addressInfo))
    }
}
