package com.norberttalpos.cart.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractAutoGeneratedIdEntity
import java.util.*
import javax.persistence.*

@Entity(name = "Cart")
@Table(name = "cart")
class Cart : AbstractAutoGeneratedIdEntity() {

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL])
    var cartItems: List<CartItem>? = emptyList()

    @Column(name = "user_id", nullable = false, updatable = false)
    var userId: Long? = null

    @Column(name = "active", nullable = false)
    var active: Boolean = true
}