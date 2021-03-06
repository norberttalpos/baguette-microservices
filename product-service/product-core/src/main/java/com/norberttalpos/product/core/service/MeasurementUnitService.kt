package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractModifiableService
import com.norberttalpos.product.api.filter.MeasurementUnitFilter
import com.norberttalpos.product.core.entity.MeasurementUnit
import com.norberttalpos.product.core.entity.QMeasurementUnit
import com.norberttalpos.product.core.repository.MeasurementUnitRepository
import org.springframework.stereotype.Service

@Service
class MeasurementUnitService
    : AbstractModifiableService<MeasurementUnit, MeasurementUnitFilter, MeasurementUnitRepository>() {

    override fun filter(filter: MeasurementUnitFilter, whereMode: WhereMode): List<MeasurementUnit> {
        val measurementUnit: QMeasurementUnit = QMeasurementUnit.measurementUnit
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(measurementUnit.id.eq(filter.id))
        }
        filter.name?.let {
            where.addUniqueStringPred(measurementUnit.name, filter.name)
        }

        return this.repository.findAll(where.builder).toList()
    }

    override fun validateEntity(entity: MeasurementUnit) = true

    override fun provideUniquenessCheckFilter(entity: MeasurementUnit) = MeasurementUnitFilter(name = entity.name)
}