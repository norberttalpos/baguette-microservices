package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.MeasurementUnitFilter
import com.norberttalpos.product.core.entity.MeasurementUnit
import com.norberttalpos.product.core.entity.QMeasurementUnit
import com.norberttalpos.product.core.entity.QProductCategory
import com.norberttalpos.product.core.repository.MeasurementUnitRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service

@Service
class MeasurementUnitService : AbstractDeletableService<MeasurementUnit, MeasurementUnitFilter, MeasurementUnitRepository>() {

    override fun filter(filter: MeasurementUnitFilter): Collection<MeasurementUnit> {
        val measurementUnit: QMeasurementUnit = QMeasurementUnit.measurementUnit
        val where = BooleanBuilder()

        filter.id?.let {
            where.and(measurementUnit.id.eq(filter.id))
        }

        return this.repository.findAll(where).toList()
    }

    override fun validateEntity(entity: MeasurementUnit): Boolean {
        return true
    }
}