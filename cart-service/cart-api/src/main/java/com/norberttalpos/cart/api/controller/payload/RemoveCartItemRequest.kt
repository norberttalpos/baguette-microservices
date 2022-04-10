package com.norberttalpos.cart.api.controller.payload

import java.util.*

data class RemoveCartItemRequest(
    val userId: UUID,
    val cartItemId: UUID
)