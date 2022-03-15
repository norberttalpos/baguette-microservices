package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto

data class ProductCategoryDto(
    override var id: Long?,
    val name: String?,
    val parent: Int?,
    val products: List<ProductDto>?,
) : AbstractDto