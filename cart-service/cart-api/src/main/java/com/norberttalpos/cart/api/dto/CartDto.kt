package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class CartDto(
    override var id: Long?,
    var userId: Long?,
    var active: Boolean?,
    var totalPrice: Double?,
    var cartItems: List<CartItemDto>?,
) : AbstractDto