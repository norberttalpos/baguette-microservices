package com.norberttalpos.cart.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import javax.persistence.*

@Entity(name = "CartItem")
@Table(name = "cart_item")
class CartItem : AbstractEntity() {

    @Column(name = "amount", nullable = false)
    var amount: Int = 0

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart_id", nullable = false)
    lateinit var cart: Cart

    @Column(name = "product_id", nullable = false)
    var productId: Long? = null
}