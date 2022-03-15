package com.norberttalpos.cart.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import org.hibernate.annotations.Cascade
import javax.persistence.*

@Entity(name = "Cart")
@Table(name = "cart")
class Cart : AbstractEntity() {

    @OneToMany(mappedBy = "cart")
    var cartItems: List<CartItem>? = emptyList()

    @Column(name = "user_id", nullable = false, updatable = false)
    var userId: Long = 0
}