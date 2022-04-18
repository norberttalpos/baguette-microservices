package com.norberttalpos.cart.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class ProductDto(
    override var id : Long?,
    val name: String?,
    val amount: Double?,
    val price: Double?,
    var brand: String?,
    var measurementUnit: String?,
    var category: String?,
) : AbstractDto
