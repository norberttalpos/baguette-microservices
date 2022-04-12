package com.norberttalpos.order.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import java.util.*
import javax.persistence.*

@Entity(name = "Order")
@Table(
    name = "order",
    uniqueConstraints = [UniqueConstraint(columnNames = ["cart_id", "customer_id"])]
)
class Order : AbstractEntity() {

    @Column(name = "cart_id", nullable = false)
    var cartId: UUID? = null

    @Column(name = "customer_id", nullable = false)
    var customerId: UUID? = null
}