package com.norberttalpos.cart.core.controller

import com.norberttalpos.cart.api.controller.CartController
import com.norberttalpos.cart.api.controller.restobjects.AddCartItemToCartRequest
import com.norberttalpos.cart.api.controller.restobjects.ModifyCartItemRequest
import com.norberttalpos.cart.api.controller.restobjects.RemoveCartItemRequest
import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.cart.core.mapper.CartItemMapper
import com.norberttalpos.cart.core.service.CartService
import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class CartControllerImpl : CartController,
    AbstractDeletableControllerImpl<CartDto, Cart, CartFilter, CartService>() {

    @Autowired
    private lateinit var cartItemMapper: CartItemMapper

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