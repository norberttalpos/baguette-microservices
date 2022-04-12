package com.norberttalpos.order.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter
import java.util.*

data class OrderFilter(
    override val id: UUID? = null,
    var cartId: UUID? = null,
    var customerId: UUID? = null,
) : AbstractFilter