package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

class MeasurementUnitDto(
    override var id: Long?,
    val name: String?,
) : AbstractDto