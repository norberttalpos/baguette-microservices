package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractModifiableController
import com.norberttalpos.product.api.dto.MeasurementUnitDto
import com.norberttalpos.product.api.filter.MeasurementUnitFilter

interface MeasurementUnitController : AbstractModifiableController<MeasurementUnitDto, MeasurementUnitFilter>