package com.norberttalpos.product.api.controller.payload

data class ProductAmountChangeRequest(
    val productName: String,
    val amount: Int,
)