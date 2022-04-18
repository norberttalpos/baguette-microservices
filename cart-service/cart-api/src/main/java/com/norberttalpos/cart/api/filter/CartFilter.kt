package com.norberttalpos.cart.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter
import java.util.*

data class CartFilter(
    override val id: Long? = null,
    val userId: Long? = null,
    val active: Boolean? = null,
) : AbstractFilter