package com.norberttalpos.cart.api.controller.restobjects

import com.norberttalpos.cart.api.dto.CartItemDto
import java.util.*

data class ModifyCartItemRequest(
    val userId: UUID,
    val cartItem: CartItemDto
)