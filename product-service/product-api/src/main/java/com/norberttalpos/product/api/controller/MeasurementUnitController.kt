package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractCreatableController
import com.norberttalpos.product.api.dto.MeasurementUnitDto
import com.norberttalpos.product.api.filter.MeasurementUnitFilter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/measurement-unit")
@Tag(name = "Measurement Unit")
interface MeasurementUnitController : AbstractCreatableController<MeasurementUnitDto, MeasurementUnitFilter>