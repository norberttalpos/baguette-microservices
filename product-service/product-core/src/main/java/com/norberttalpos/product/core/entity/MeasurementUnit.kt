package com.norberttalpos.product.core.entity

import com.norberttalpos.abstracts.entity.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "MeasurementUnit")
@Table(name = "measurement_unit")
class MeasurementUnit : AbstractEntity() {

    @Column(name = "name", nullable = false)
    val name: String = ""
}