package com.norberttalpos.product.api.dto

import com.norberttalpos.abstracts.dto.AbstractDto

class MeasurementUnitDto(
    override val id: Long,
    val name: String
) : AbstractDto