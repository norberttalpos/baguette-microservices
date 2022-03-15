package com.norberttalpos.cart.core.service

import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.cart.core.entity.CartItem
import com.norberttalpos.cart.core.entity.QCart
import com.norberttalpos.cart.core.repository.CartItemRepository
import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.common.exception.NotValidUpdateException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CartService : AbstractDeletableService<Cart, CartFilter>() {

    @Autowired
    private lateinit var cartItemRepository: CartItemRepository

    override fun filter(filter: CartFilter, whereMode: WhereMode): List<Cart> {
        val cart: QCart = QCart.cart
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(cart.id.eq(filter.id))
        }
        filter.userId?.let {
            where.add(cart.id.eq(filter.userId))
        }

        return this.repository.findAll(where.getBuilder()).toList()
    }

    override fun validateEntity(entity: Cart) = true

    override fun provideUniqunessCheckFilter(entity: Cart) = CartFilter(userId = entity.userId)

    fun modifyCartItem(userId: Long, cartItem: CartItem) {
        val carts = this.filter(CartFilter(userId = userId))
        if(carts.size == 1) {
            this.put(carts.first().apply {
                this.cartItems = this.cartItems?.
                filter { it.id == cartItem.id }?.
                map { it.apply { this.amount = cartItem.amount } }
            })
        } else throw NotValidUpdateException("user with id $userId doesn't have a cart")
    }

    fun removeCartItem(userId: Long, cartItemId: Long) {
        val carts = this.filter(CartFilter(userId = userId))
        if(carts.size == 1) {
            carts.first().cartItems?.
            filter { it.id == cartItemId }?.
            forEach { this.cartItemRepository.deleteById(it.id!!) }
        } else throw NotValidUpdateException("user with id $userId doesn't have a cart")
    }

    fun emptyCart(userId: Long) {
        val carts = this.filter(CartFilter(userId = userId))
        if(carts.size == 1) {
            carts.first().cartItems?.forEach { this.cartItemRepository.deleteById(it.id!!) }
        } else throw NotValidUpdateException("user with id $userId doesn't have a cart")
    }
}