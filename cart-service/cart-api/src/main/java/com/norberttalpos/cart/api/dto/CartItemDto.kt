package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class CartItemDto(
    override var id: UUID? = null,
    var amount: Int? = null,
    var price: Double? = null,
    var product: ProductDto? = null,
) : AbstractDto