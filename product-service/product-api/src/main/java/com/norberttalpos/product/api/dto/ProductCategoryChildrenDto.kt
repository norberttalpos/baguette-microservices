package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto

data class ProductCategoryChildrenDto(
    override var id: Long?,
    val name: String,
    val children: MutableList<ProductCategoryChildrenDto>,
) : AbstractDto