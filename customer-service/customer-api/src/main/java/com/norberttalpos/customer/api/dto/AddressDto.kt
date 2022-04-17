package com.norberttalpos.customer.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class AddressDto(
    override var id: UUID? = null,
    var city: String? = null,
    var street: String? = null,
    var postalCode: Int? = null,
) : AbstractDto