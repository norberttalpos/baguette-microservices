package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto

data class CartItemDto(
    override var id: Long?,
    var amount: Int?,
    var price: Double?,
    var product: ProductDto?
) : AbstractDto