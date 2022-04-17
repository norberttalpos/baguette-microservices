package com.norberttalpos.cart.core.controller

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.cart.api.controller.CartController
import com.norberttalpos.cart.api.controller.payload.AddCartItemToCartRequest
import com.norberttalpos.cart.api.controller.payload.CreateCartRequest
import com.norberttalpos.cart.api.controller.payload.ModifyCartItemRequest
import com.norberttalpos.cart.api.controller.payload.RemoveCartItemRequest
import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.cart.core.mapper.CartItemMapper
import com.norberttalpos.cart.core.mapper.CartMapper
import com.norberttalpos.cart.core.service.CartService
import com.norberttalpos.common.abstracts.controller.implementations.AbstractGettableControllerImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class CartControllerImpl(
    private val cartItemMapper: CartItemMapper,
    private val cartMapper: CartMapper,
) : CartController,
    AbstractGettableControllerImpl<CartDto, Cart, CartFilter, CartService>() {

    override fun create(createCartRequest: CreateCartRequest)
        = this.commandMethod {
            this.service.createCart(createCartRequest.userId!!)
        }


    override fun getCurrentCartOfCustomer(currentUser: UserDto)
        = this.commandMethod {
            this.cartMapper.toDto(
                this.service.getCartOfUser(currentUser.id!!)
            )
        }

    override fun addProductToCart(request: AddCartItemToCartRequest, currentUser: UserDto)
        = this.commandMethod {
            this.cartMapper.toDto(
                this.service.addProductToCart(currentUser.id!!, request.productId!!)
            )
        }

    override fun modifyCartItem(request: ModifyCartItemRequest, currentUser: UserDto)
        = this.commandMethod {
            this.cartMapper.toDto(
                this.service.modifyCartItem(
                    currentUser.id!!,
                    this.cartItemMapper.fromDto(request.cartItem!!)
                )
            )
        }

    override fun removeCartItem(request: RemoveCartItemRequest, currentUser: UserDto)
        = this.commandMethod {
            this.cartMapper.toDto(
                this.service.removeCartItem(currentUser.id!!, request.cartItemId!!)
            )
        }

    override fun emptyCart(currentUser: UserDto)
        = this.commandMethod {
            this.cartMapper.toDto(
                this.service.emptyCart(currentUser.id!!)
            )
        }

    override fun createOrder(currentUser: UserDto)
        = this.commandMethod {
            this.service.createOrder(currentUser.id!!)
        }

    override fun deleteCustomerCarts(currentUser: UserDto): ResponseEntity<Unit>
        = this.commandMethod {
            this.service.deleteCustomerCarts(currentUser.id!!)
        }
}