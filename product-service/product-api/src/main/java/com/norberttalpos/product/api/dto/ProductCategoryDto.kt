package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class ProductCategoryDto(
    override var id: UUID? = null,
    val name: String? = null,
    val parent: Int? = null,
    val products: List<ProductDto>? = null,
) : AbstractDto