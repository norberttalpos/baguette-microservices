package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class ProductDto(
    override var id : UUID? = null,
    val name: String? = null,
    val amount: Double? = null,
    val price: Double? = null,
    var brand: String? = null,
    var measurementUnit: String? = null,
    var category: String? = null,
) : AbstractDto
