package com.norberttalpos.customer.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import javax.persistence.*

@Entity(name = "Address")
@Table(
    name = "address",
    uniqueConstraints = [UniqueConstraint(columnNames = ["city", "street", "postal_code"])]
)
class Address : AbstractEntity() {

    @Column(name = "city", nullable = false)
    var city: String = ""

    @Column(name = "street", nullable = false)
    var street: String = ""

    @Column(name = "postal_code", nullable = false)
    var postalCode: Int = 9999

    @OneToMany(mappedBy = "address")
    var customersAtAddress: List<Customer>? = emptyList()
}