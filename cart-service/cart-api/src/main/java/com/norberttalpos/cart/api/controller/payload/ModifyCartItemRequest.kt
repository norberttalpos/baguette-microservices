package com.norberttalpos.cart.api.controller.payload

import com.norberttalpos.cart.api.dto.CartItemDto

data class ModifyCartItemRequest(
    var cartItem: CartItemDto? = null
)