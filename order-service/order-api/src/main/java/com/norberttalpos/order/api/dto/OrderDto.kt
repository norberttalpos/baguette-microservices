package com.norberttalpos.order.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class OrderDto(
    override var id: UUID? = null,
    var cartId: UUID?,
    var customerId: UUID?,
) : AbstractDto