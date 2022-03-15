package com.norberttalpos.cart.api.controller.restobjects

data class AddCartItemToCartRequest(
    val userId: Long,
    val productId: Long
)