package com.norberttalpos.customer.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity(name = "Address")
@Table(name = "address")
class Address : AbstractEntity() {

    @Column(name = "city", nullable = false)
    var city: String = ""

    @Column(name = "street", nullable = false)
    var street: String = ""

    @Column(name = "postal_code", nullable = false)
    var postalCode: Int = 9999
}