package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class ProductDto(
    override var id : UUID? = null,
    val name: String? = null,
    val unitPrice: Int? = null,
    val amount: Double? = null,
    var price: Double? = null,
    val quantity: Int? = null,
    val rating: Double? = null,
    val brand: ProductBrandDto? = null,
    val measurementUnit: MeasurementUnitDto? = null,
    val category: ProductCategoryDto? = null,
) : AbstractDto