package com.norberttalpos.product.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.product.api.dto.MeasurementUnitDto
import com.norberttalpos.product.core.entity.MeasurementUnit
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
abstract class MeasurementUnitMapper : AbstractDtoMapper<MeasurementUnit, MeasurementUnitDto>()