package com.norberttalpos.cart.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter

data class CartFilter(
    override val id: Long? = null,
    val userId: Long? = null,
) : AbstractFilter