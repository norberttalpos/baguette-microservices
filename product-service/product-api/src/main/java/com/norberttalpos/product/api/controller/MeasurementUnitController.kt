package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractModifiableController
import com.norberttalpos.product.api.dto.MeasurementUnitDto
import com.norberttalpos.product.api.filter.MeasurementUnitFilter
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/measurement-unit")
interface MeasurementUnitController : AbstractModifiableController<MeasurementUnitDto, MeasurementUnitFilter>