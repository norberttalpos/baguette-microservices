package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto

data class CartDto(
    override var id: Long?,
    var userId: Long?,
    var totalPrice: Double?,
    var cartItems: List<CartItemDto>?
) : AbstractDto