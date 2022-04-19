package com.norberttalpos.product.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter
import java.util.*

data class ProductCategoryFilter(
    override val id: Long? = null,
    val name: String? = null,
) : AbstractFilter