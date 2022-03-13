package com.norberttalpos.product.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "MeasurementUnit")
@Table(name = "measurement_unit")
class MeasurementUnit : AbstractEntity() {

    @Column(name = "name", nullable = false, unique = true)
    var name: String = ""
}