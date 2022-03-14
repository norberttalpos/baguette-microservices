package com.norberttalpos.product.api.controller.restobjects

data class ProductAmountChangeRequest(
    val productName: String,
    val amount: Int,
)