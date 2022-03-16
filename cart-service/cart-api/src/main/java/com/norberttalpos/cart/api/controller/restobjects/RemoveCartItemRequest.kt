package com.norberttalpos.cart.api.controller.restobjects

import java.util.*

data class RemoveCartItemRequest(
    val userId: UUID,
    val cartItemId: UUID
)