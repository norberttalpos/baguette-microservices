package com.norberttalpos.product.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter
import java.util.*

data class ProductFilter(
    override val id: UUID? = null,
    val name: String? = null,
    val brandName: String? = null,
    val categoryName: String? = null,
) : AbstractFilter