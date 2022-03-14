package com.norberttalpos.product.core.service

import com.norberttalpos.common.QueryBuilder
import com.norberttalpos.common.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.MeasurementUnitFilter
import com.norberttalpos.product.core.entity.MeasurementUnit
import com.norberttalpos.product.core.entity.QMeasurementUnit
import org.springframework.stereotype.Service

@Service
class MeasurementUnitService : AbstractDeletableService<MeasurementUnit, MeasurementUnitFilter>() {

    override fun filter(filter: MeasurementUnitFilter, whereMode: WhereMode): Collection<MeasurementUnit> {
        val measurementUnit: QMeasurementUnit = QMeasurementUnit.measurementUnit
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(measurementUnit.id.eq(filter.id))
        }
        filter.name?.let {
            where.addUniqueStringPred(measurementUnit.name, filter.name)
        }

        return this.repository.findAll(where.getBooleanBuilder()).toList()
    }

    override fun validateEntity(entity: MeasurementUnit): Boolean {
        val collisions = this.filter(MeasurementUnitFilter(name = entity.name), WhereMode.OR)
        return collisions.isEmpty()
    }
}