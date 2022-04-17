package com.norberttalpos.customer.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class CustomerDto(
    override var id: UUID?,
    val name: String?,
    val email: String?,
    val phoneNumber: String?,
    val imageUrl: String?,
    val address: AddressDto?,
) : AbstractDto