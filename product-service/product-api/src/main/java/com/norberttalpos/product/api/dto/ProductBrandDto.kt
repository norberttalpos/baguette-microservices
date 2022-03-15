package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto

data class ProductBrandDto(
    override var id : Long?,
    val name: String?,
    val country: String?,
    val products: List<ProductDto>?
) : AbstractDto