package com.norberttalpos.order.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.order.api.dto.OrderStatus
import java.util.*

data class OrderFilter(
    override val id: Long? = null,
    var cartId: Long? = null,
    var customerId: Long? = null,
    var status: OrderStatus? = null,
) : AbstractFilter