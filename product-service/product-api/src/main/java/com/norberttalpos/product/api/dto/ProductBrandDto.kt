package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class ProductBrandDto(
    override var id : UUID? = null,
    val name: String? = null,
    val country: String? = null,
    val products: List<ProductDto>? = null,
) : AbstractDto