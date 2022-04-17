package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class ProductCategoryDto(
    override var id: UUID?,
    val name: String?,
    val parent: Int?,
    val products: List<ProductDto>?,
) : AbstractDto