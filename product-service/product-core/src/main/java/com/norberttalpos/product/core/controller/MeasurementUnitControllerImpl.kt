package com.norberttalpos.product.core.controller

import com.norberttalpos.common.abstracts.controller.implementations.AbstractModifiableControllerImpl
import com.norberttalpos.product.api.controller.MeasurementUnitController
import com.norberttalpos.product.api.dto.MeasurementUnitDto
import com.norberttalpos.product.api.filter.MeasurementUnitFilter
import com.norberttalpos.product.core.entity.MeasurementUnit
import com.norberttalpos.product.core.mapper.MeasurementUnitMapper
import com.norberttalpos.product.core.service.MeasurementUnitService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/measurement-unit")
class MeasurementUnitControllerImpl : MeasurementUnitController,
    AbstractModifiableControllerImpl<MeasurementUnitDto, MeasurementUnit, MeasurementUnitFilter, MeasurementUnitMapper, MeasurementUnitService>()