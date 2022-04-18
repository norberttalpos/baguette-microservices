package com.norberttalpos.customer.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class AddressDto(
    override var id: Long?,
    var city: String?,
    var street: String?,
    var postalCode: Int?,
) : AbstractDto