package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class CartItemDto(
    override var id: UUID?,
    var amount: Int?,
    var price: Double?,
    var product: ProductDto?,
) : AbstractDto