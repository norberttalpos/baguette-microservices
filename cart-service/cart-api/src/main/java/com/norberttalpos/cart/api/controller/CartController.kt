package com.norberttalpos.cart.api.controller

import com.norberttalpos.cart.api.controller.restobjects.AddCartItemToCartRequest
import com.norberttalpos.cart.api.controller.restobjects.ModifyCartItemRequest
import com.norberttalpos.cart.api.controller.restobjects.RemoveCartItemRequest
import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import org.springframework.http.ResponseEntity

interface CartController : AbstractDeletableController<CartDto, CartFilter> {

    fun addProductToCart(request: AddCartItemToCartRequest)

    fun modifyCartItem(request: ModifyCartItemRequest)

    fun removeCartItem(request: RemoveCartItemRequest)

    fun emptyCart(userId: Long)
}