package com.norberttalpos.cart.api.controller.payload

import java.util.*

data class AddCartItemToCartRequest(
    val userId: UUID,
    val productId: UUID
)