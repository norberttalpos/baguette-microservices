package com.norberttalpos.order.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class OrderDto(
    override var id: Long? = null,
    var cartId: Long?,
    var customerId: Long?,
) : AbstractDto