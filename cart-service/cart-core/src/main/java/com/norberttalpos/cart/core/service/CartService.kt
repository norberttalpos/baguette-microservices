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
import com.norberttalpos.product.api.client.CartProductResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CartService : AbstractDeletableService<Cart, CartFilter>() {

    @Autowired
    private lateinit var cartItemRepository: CartItemRepository

    @Autowired
    private lateinit var productGetterService: CartProductResource

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

    fun addProductToCart(userId: Long, productId: Long) {
        val cart = this.getCartOfUser(userId)

        if(this.productGetterService.getProductById(productId) != null) {
            this.cartItemRepository.save(
                CartItem().apply {
                    this.productId = productId
                    this.amount = 1
                    this.cart = cart
                }
            )
        }
    }

    fun modifyCartItem(userId: Long, cartItem: CartItem) {
        val cart = this.getCartOfUser(userId)
        this.put(cart.apply {
            this.cartItems = this.cartItems?.
            filter { it.id == cartItem.id }?.
            map { it.apply { this.amount = cartItem.amount } }
        })
    }

    fun removeCartItem(userId: Long, cartItemId: Long) {
        val cart = this.getCartOfUser(userId)
        cart.cartItems?.filter { it.id == cartItemId }?.forEach { this.cartItemRepository.deleteById(it.id!!) }
    }

    fun emptyCart(userId: Long) {
        val cart = this.getCartOfUser(userId)
        cart.cartItems?.forEach { this.cartItemRepository.deleteById(it.id!!) }
    }

    private fun getCartOfUser(userId: Long): Cart {
        val carts = this.filter(CartFilter(userId = userId))
        if(carts.size == 1) {
            return carts.first()
        } else throw NotValidUpdateException("user with id $userId doesn't have a cart")
    }
}