package com.norberttalpos.cart.api.controller.payload

import java.util.*

data class AddCartItemToCartRequest(
    var productId: Long? = null
)