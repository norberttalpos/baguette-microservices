package com.norberttalpos.cart.core.service

import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.cart.core.entity.CartItem
import com.norberttalpos.cart.core.entity.QCart
import com.norberttalpos.cart.core.repository.CartItemRepository
import com.norberttalpos.cart.core.repository.CartRepository
import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.common.exception.NotValidUpdateException
import com.norberttalpos.customer.api.client.CustomerClient
import com.norberttalpos.product.api.client.CartProductResource
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartService(
    private val cartItemRepository: CartItemRepository,
    private val productGetterService: CartProductResource,
    private val customerClient: CustomerClient
) : AbstractDeletableService<Cart, CartFilter, CartRepository>() {

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

    override fun provideUniquenessCheckFilter(entity: Cart) = CartFilter(userId = entity.userId)

    fun addProductToCart(userId: UUID, productId: UUID): Cart {
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

        return cart
    }

    fun modifyCartItem(userId: UUID, cartItem: CartItem): Cart {
        val cart = this.getCartOfUser(userId)
        this.put(cart.apply {
            this.cartItems = this.cartItems
                ?.filter { it.id == cartItem.id }
                ?.map { it.apply { this.amount = cartItem.amount } }
        })

        return cart
    }

    fun removeCartItem(userId: UUID, cartItemId: UUID): Cart {
        val cart = this.getCartOfUser(userId)
        cart.cartItems
            ?.filter { it.id == cartItemId }
            ?.forEach { this.cartItemRepository.deleteById(it.id!!) }

        return cart
    }

    fun emptyCart(userId: UUID): Cart {
        val cart = this.getCartOfUser(userId)
        cart.cartItems?.forEach { this.cartItemRepository.deleteById(it.id!!) }

        return cart
    }

    fun createOrder(userId: UUID) {
        // TODO
    }

    fun createCart(userId: UUID): UUID {

        if(this.customerClient.userExistsById(userId).body!!) {
            try {
                this.getCartOfUser(userId)

            } catch (e: Exception) {

                val cartCreated = this.repository.saveAndFlush(
                    Cart().apply {
                        this.userId = userId
                    }
                )

                return cartCreated.id!!
            }
        } else {
            throw NotValidUpdateException("Customer by id doesn't exist")
        }

        throw NotValidUpdateException("Customer already has a cart")
    }

    private fun getCartOfUser(userId: UUID): Cart {
        val carts = this.filter(CartFilter(userId = userId))
        if(carts.size == 1) {
            return carts.first()
        } else throw NotValidUpdateException("user with id $userId doesn't have a cart")
    }
}