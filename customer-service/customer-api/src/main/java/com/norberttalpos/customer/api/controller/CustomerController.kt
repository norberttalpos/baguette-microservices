package com.norberttalpos.customer.api.controller

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.auth.api.util.CurrentUser
import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.common.abstracts.controller.interfaces.AbstractModifiableController
import com.norberttalpos.customer.api.dto.AddressDto
import com.norberttalpos.customer.api.dto.CustomerDto
import com.norberttalpos.customer.api.filter.CustomerFilter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer")
interface CustomerController : AbstractModifiableController<CustomerDto, CustomerFilter> {

    @PutMapping("/addAddressInfo")
    fun addAddressInfo(@RequestBody addressInfo: AddressDto, @CurrentUser currentUser: UserDto)

    @GetMapping("/me")
    fun currentUser(@CurrentUser currentUser: UserDto): ResponseEntity<CustomerDto>

    @GetMapping("/{id}/userExistsById")
    fun userExistsById(@PathVariable id: UUID): ResponseEntity<Boolean>

    @DeleteMapping
    fun deleteUser(@CurrentUser currentUser: UserDto): ResponseEntity<Any>
}