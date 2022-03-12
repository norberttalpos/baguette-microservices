package com.norberttalpos.product.api.dto

import com.norberttalpos.abstracts.dto.AbstractDto

data class ProductCategoryDto(
    override val id: Long,
    val name: String,
    val parent: Int,
    val products: Collection<ProductDto>,
) : AbstractDto