package com.norberttalpos.customer.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class AddressDto(
    override var id: UUID?,
    val city: String?,
    val street: String?,
    val postalCode: Int?,
) : AbstractDto