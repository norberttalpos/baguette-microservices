package com.norberttalpos.product.core.mapper

import com.norberttalpos.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.product.api.dto.MeasurementUnitDto
import com.norberttalpos.product.core.entity.MeasurementUnit
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
abstract class MeasurementUnitMapper : AbstractDtoMapper<MeasurementUnit, MeasurementUnitDto>