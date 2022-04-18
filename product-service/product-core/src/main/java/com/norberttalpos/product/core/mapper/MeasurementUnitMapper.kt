package com.norberttalpos.product.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import com.norberttalpos.product.api.dto.MeasurementUnitDto
import com.norberttalpos.product.core.entity.MeasurementUnit
import org.mapstruct.Mapper

@Mapper(config = MapstructConfig::class)
abstract class MeasurementUnitMapper : AbstractDtoMapper<MeasurementUnit, MeasurementUnitDto>()