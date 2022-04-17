package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class ProductBrandDto(
    override var id : UUID?,
    val name: String?,
    val country: String?,
    val products: List<ProductDto>?,
) : AbstractDto