package com.norberttalpos.cart.api.controller.restobjects

import com.norberttalpos.cart.api.dto.CartItemDto

data class ModifyCartItemRequest(
    val userId: Long,
    val cartItem: CartItemDto
)