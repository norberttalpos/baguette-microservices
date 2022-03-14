package com.norberttalpos.cart.core.service

import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.common.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import org.springframework.stereotype.Service

@Service
class CartService : AbstractDeletableService<Cart, CartFilter>() {

    override fun filter(filter: CartFilter, whereMode: WhereMode): Collection<Cart> {
        return this.getEntities()
    }

    override fun validateEntity(entity: Cart): Boolean {
        val collisions = this.filter(CartFilter(userId = entity.userId), WhereMode.OR)
        return collisions.isEmpty()
    }
}