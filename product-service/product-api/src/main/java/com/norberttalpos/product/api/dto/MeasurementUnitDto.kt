package com.norberttalpos.product.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

class MeasurementUnitDto(
    override var id: UUID?,
    val name: String?,
) : AbstractDto