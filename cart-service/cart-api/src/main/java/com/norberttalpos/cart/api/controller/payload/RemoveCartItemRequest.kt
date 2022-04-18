package com.norberttalpos.cart.api.controller.payload

import java.util.*

data class RemoveCartItemRequest(
    var cartItemId: Long? = null
)