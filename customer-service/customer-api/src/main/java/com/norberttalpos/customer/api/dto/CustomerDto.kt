package com.norberttalpos.customer.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class CustomerDto(
    override var id: UUID? = null,
    val name: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
    val imageUrl: String? = null,
    val address: AddressDto? = null,
) : AbstractDto