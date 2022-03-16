package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class CartDto(
    override var id: UUID?,
    var userId: UUID?,
    var totalPrice: Double?,
    var cartItems: List<CartItemDto>?
) : AbstractDto