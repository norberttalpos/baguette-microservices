package com.norberttalpos.cart.api.controller.restobjects

import java.util.*

data class AddCartItemToCartRequest(
    val userId: UUID,
    val productId: UUID
)