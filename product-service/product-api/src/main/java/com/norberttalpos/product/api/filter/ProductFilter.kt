package com.norberttalpos.product.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter

data class ProductFilter(
    override val id: Long?,
    val brandName: String?,
    val categoryName: String?,
) : AbstractFilter