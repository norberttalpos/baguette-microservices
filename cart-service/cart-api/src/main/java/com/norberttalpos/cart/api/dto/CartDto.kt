package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class CartDto(
    override var id: UUID? = null,
    var userId: UUID? = null,
    var active: Boolean? = null,
    var totalPrice: Double? = null,
    var cartItems: List<CartItemDto>? = null,
) : AbstractDto