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
import com.norberttalpos.order.api.client.OrderClient
import com.norberttalpos.order.api.dto.OrderDto
import com.norberttalpos.product.api.client.CartProductResource
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import java.util.*

@Service
class CartService(
    private val cartItemRepository: CartItemRepository,
    private val productGetterService: CartProductResource,
    private val customerClient: CustomerClient,
    private val orderClient: OrderClient,
) : AbstractDeletableService<Cart, CartFilter, CartRepository>() {

    override fun filter(filter: CartFilter, whereMode: WhereMode): List<Cart> {
        val cart: QCart = QCart.cart
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(cart.id.eq(filter.id))
        }
        filter.userId?.let {
            where.add(cart.userId.eq(filter.userId))
        }
        filter.active?.let {
            where.add(cart.active.eq(filter.active))
        }

        return this.repository.findAll(where.builder).toList()
    }

    override fun validateEntity(entity: Cart) = true

    override fun provideUniquenessCheckFilter(entity: Cart) = CartFilter(userId = entity.userId)

    fun addProductToCart(userId: UUID, productId: UUID): Cart {
        val cart = this.getCartOfUser(userId)

        try {
            if(this.productGetterService.getProductById(productId) != null) {
                this.cartItemRepository.save(
                    CartItem().apply {
                        this.productId = productId
                        this.amount = 1
                        this.cart = cart
                    }
                )
            }

            logger.info { "Added product $productId to cart ${cart.id}" }
        } catch(e: Exception) {

            logger.error { "Product by id $productId not found" }
            throw NotValidUpdateException("Product by id $productId not found")
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

        logger.info { "Modified cart item ${cartItem.id}" }

        return cart
    }

    fun removeCartItem(userId: UUID, cartItemId: UUID): Cart {
        val cart = this.getCartOfUser(userId)
        cart.cartItems
            ?.filter { it.id == cartItemId }
            ?.forEach { this.cartItemRepository.deleteById(it.id!!) }

        logger.info { "Removed cart item $cartItemId" }

        return cart
    }

    fun emptyCart(userId: UUID): Cart {
        val cart = this.getCartOfUser(userId)
        cart.cartItems?.forEach { this.cartItemRepository.deleteById(it.id!!) }

        logger.info { "Emptied the cart of user $userId" }

        return cart
    }

    fun createOrder(userId: UUID) {
        val cartOfUser = this.getCartOfUser(userId)

        val jwtToken = SecurityContextHolder.getContext().authentication.credentials as? String

        jwtToken?.let {

            this.put(cartOfUser.apply {
                this.active = false
            })

            logger.info { "Trying to create order for user $userId" }

            this.orderClient.createOrder(
                OrderDto(customerId = userId, cartId = cartOfUser.id!!),
                jwtToken
            )

            logger.info { "Created order for user $userId" }

            this.createCart(userId)

        } ?: run {
            logger.error { "Operation requires a user token" }
            throw NotValidUpdateException("Operation requires a user token")
        }
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

                logger.info { "Created cart for customer $userId" }

                return cartCreated.id!!
            }
        } else {
            logger.info { "Customer $userId already has an active cart" }
            throw NotValidUpdateException("Customer by id $userId doesn't exist")
        }

        logger.info { "Customer $userId already has an active cart" }

        throw NotValidUpdateException("Customer $userId already has an active cart")
    }

    fun getCartOfUser(userId: UUID): Cart {
        val carts = this.filter(CartFilter(userId = userId, active = true))
        if(carts.size == 1) {
            return carts.first()
        } else {
            logger.error { "Illegal state: Customer $userId doesn't have exactly 1 active cart" }
            throw IllegalStateException()
        }
    }
}