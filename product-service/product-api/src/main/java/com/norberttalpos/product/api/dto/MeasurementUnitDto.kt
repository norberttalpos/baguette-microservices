package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto

class MeasurementUnitDto(
    override var id: Long?,
    val name: String?,
) : AbstractDto