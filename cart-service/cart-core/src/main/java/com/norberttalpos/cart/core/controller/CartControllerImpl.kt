package com.norberttalpos.cart.core.controller

import com.norberttalpos.cart.api.controller.CartController
import com.norberttalpos.cart.api.controller.restobjects.AddCartItemToCartRequest
import com.norberttalpos.cart.api.controller.restobjects.ModifyCartItemRequest
import com.norberttalpos.cart.api.controller.restobjects.RemoveCartItemRequest
import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.cart.core.mapper.CartItemMapper
import com.norberttalpos.cart.core.mapper.CartMapper
import com.norberttalpos.cart.core.service.CartService
import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/cart")
class CartControllerImpl : CartController,
    AbstractDeletableControllerImpl<CartDto, Cart, CartFilter, CartMapper, CartService>() {

    @Autowired
    private lateinit var cartItemMapper: CartItemMapper

    @PostMapping("/add-product-to-cart")
    override fun addProductToCart(@RequestBody request: AddCartItemToCartRequest) {
        this.service.addProductToCart(request.userId, request.productId)
    }

    @PutMapping("/modify-cart-item")
    override fun modifyCartItem(@RequestBody request: ModifyCartItemRequest) {
        this.service.modifyCartItem(request.userId, this.cartItemMapper.fromDto(request.cartItem))
    }

    @PutMapping("/remove-cart-item")
    override fun removeCartItem(@RequestBody request: RemoveCartItemRequest) {
       this.service.removeCartItem(request.userId, request.cartItemId)
    }

    @PutMapping("/{userId}/empty")
    override fun emptyCart(@PathVariable userId: UUID) {
        this.service.emptyCart(userId)
    }
}