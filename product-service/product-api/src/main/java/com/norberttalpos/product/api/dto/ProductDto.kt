package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto

data class ProductDto(
    override var id : Long?,
    val name: String?,
    val unitPrice: Int?,
    val amount: Int?,
    val rating: Float?,
    val brand: ProductBrandDto?,
    val measurementUnit: MeasurementUnitDto?,
    val category: ProductCategoryDto?,
) : AbstractDto