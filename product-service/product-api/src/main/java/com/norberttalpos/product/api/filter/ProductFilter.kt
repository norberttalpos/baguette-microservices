package com.norberttalpos.product.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter

data class ProductFilter(
    override val id: Long? = null,
    val name: String? = null,
    val brandName: String? = null,
    val categoryName: String? = null,
) : AbstractFilter