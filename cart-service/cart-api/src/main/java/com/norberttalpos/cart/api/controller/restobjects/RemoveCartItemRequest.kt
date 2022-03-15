package com.norberttalpos.cart.api.controller.restobjects

data class RemoveCartItemRequest(
    val userId: Long,
    val cartItemId: Long
)