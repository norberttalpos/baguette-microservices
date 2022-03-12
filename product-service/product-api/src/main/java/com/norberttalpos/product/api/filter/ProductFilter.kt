package com.norberttalpos.product.api.filter

import com.norberttalpos.abstracts.filter.AbstractFilter

data class ProductFilter(
    override val id: Long
) : AbstractFilter