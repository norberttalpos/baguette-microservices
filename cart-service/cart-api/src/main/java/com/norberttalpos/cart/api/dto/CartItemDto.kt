package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.product.api.dto.ProductDto

data class CartItemDto(
    override var id: Long?,
    var amount: Int?,
    var price: Double?,
    var product: ProductDto?
) : AbstractDto