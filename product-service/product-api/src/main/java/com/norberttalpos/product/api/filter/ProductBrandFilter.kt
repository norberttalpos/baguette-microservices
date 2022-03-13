package com.norberttalpos.product.api.filter

import com.norberttalpos.common.abstracts.filter.AbstractFilter

data class ProductBrandFilter(
    override val id: Long?,
) : AbstractFilter