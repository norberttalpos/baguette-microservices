package com.norberttalpos.cart.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity(name = "Cart")
@Table(name = "cart")
class Cart : AbstractEntity() {

    @OneToMany(mappedBy = "cart")
    var cartItems: List<CartItem>? = emptyList()

    @Column(name = "user_id", nullable = false, updatable = false)
    lateinit var userId: UUID
}