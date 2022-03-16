package com.norberttalpos.cart.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter
import java.util.*

data class CartFilter(
    override val id: UUID? = null,
    val userId: UUID? = null,
) : AbstractFilter