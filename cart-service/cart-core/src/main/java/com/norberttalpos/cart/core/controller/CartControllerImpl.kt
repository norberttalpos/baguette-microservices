package com.norberttalpos.cart.core.controller

import com.norberttalpos.cart.api.controller.CartController
import com.norberttalpos.cart.api.controller.payload.AddCartItemToCartRequest
import com.norberttalpos.cart.api.controller.payload.ModifyCartItemRequest
import com.norberttalpos.cart.api.controller.payload.RemoveCartItemRequest
import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.cart.core.mapper.CartItemMapper
import com.norberttalpos.cart.core.service.CartService
import com.norberttalpos.common.abstracts.controller.implementations.AbstractModifiableControllerImpl
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class CartControllerImpl(
    private var cartItemMapper: CartItemMapper,
) : CartController,
    AbstractModifiableControllerImpl<CartDto, Cart, CartFilter, CartService>() {

    override fun addProductToCart(request: AddCartItemToCartRequest) {
        this.service.addProductToCart(request.userId, request.productId)
    }

    override fun modifyCartItem(request: ModifyCartItemRequest) {
        this.service.modifyCartItem(request.userId, this.cartItemMapper.fromDto(request.cartItem))
    }

    override fun removeCartItem(request: RemoveCartItemRequest) {
       this.service.removeCartItem(request.userId, request.cartItemId)
    }

    override fun emptyCart(userId: UUID) {
        this.service.emptyCart(userId)
    }
}