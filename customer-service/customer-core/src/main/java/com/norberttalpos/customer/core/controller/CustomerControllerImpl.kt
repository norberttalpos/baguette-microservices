package com.norberttalpos.customer.core.controller

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.common.abstracts.controller.implementations.AbstractModifiableControllerImpl
import com.norberttalpos.customer.api.controller.CustomerController
import com.norberttalpos.customer.api.dto.AddressDto
import com.norberttalpos.customer.api.dto.CustomerDto
import com.norberttalpos.customer.api.filter.CustomerFilter
import com.norberttalpos.customer.core.entity.Customer
import com.norberttalpos.customer.core.mapper.AddressMapper
import com.norberttalpos.customer.core.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class CustomerControllerImpl(
    private val addressMapper: AddressMapper,
) : CustomerController,
    AbstractModifiableControllerImpl<CustomerDto, Customer, CustomerFilter, CustomerService>() {

    override val clearId: Boolean
        get() = false

    override fun addAddressInfo(addressInfo: AddressDto, currentUser: UserDto) {
        this.service.addAddressInfo(addressMapper.fromDto(addressInfo), currentUser)
    }

    override fun currentUser(currentUser: UserDto): ResponseEntity<CustomerDto> {
        return ResponseEntity.ok(this.mapper.toDto(this.service.getByEmail(currentUser.email!!)!!))
    }

    override fun userExistsById(id: Long): ResponseEntity<Boolean> {
        return ResponseEntity.ok(this.service.userExistsById(id))
    }

    override fun deleteUser(currentUser: UserDto): ResponseEntity<Any> {
        return ResponseEntity.ok(this.service.deleteById(currentUser.id!!))
    }
}
