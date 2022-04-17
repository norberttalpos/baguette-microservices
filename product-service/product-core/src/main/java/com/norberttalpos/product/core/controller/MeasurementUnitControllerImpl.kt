package com.norberttalpos.product.core.controller

import com.norberttalpos.common.abstracts.controller.implementations.AbstractCreatableControllerImpl
import com.norberttalpos.product.api.controller.MeasurementUnitController
import com.norberttalpos.product.api.dto.MeasurementUnitDto
import com.norberttalpos.product.api.filter.MeasurementUnitFilter
import com.norberttalpos.product.core.entity.MeasurementUnit
import com.norberttalpos.product.core.service.MeasurementUnitService
import org.springframework.web.bind.annotation.RestController

@RestController
class MeasurementUnitControllerImpl : MeasurementUnitController,
    AbstractCreatableControllerImpl<MeasurementUnitDto, MeasurementUnit, MeasurementUnitFilter, MeasurementUnitService>()