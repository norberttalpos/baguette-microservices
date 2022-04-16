package com.norberttalpos.order.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.order.api.dto.OrderStatus
import java.util.*

data class OrderFilter(
    override val id: UUID? = null,
    var cartId: UUID? = null,
    var customerId: UUID? = null,
    var status: OrderStatus? = null,
) : AbstractFilter