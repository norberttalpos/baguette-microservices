package com.norberttalpos.cart.api.controller

import com.norberttalpos.cart.api.controller.restobjects.AddCartItemToCartRequest
import com.norberttalpos.cart.api.controller.restobjects.ModifyCartItemRequest
import com.norberttalpos.cart.api.controller.restobjects.RemoveCartItemRequest
import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/cart")
interface CartController : AbstractDeletableController<CartDto, CartFilter> {

    @PostMapping("/add-product-to-cart")
    fun addProductToCart(@RequestBody request: AddCartItemToCartRequest)

    @PutMapping("/modify-cart-item")
    fun modifyCartItem(@RequestBody request: ModifyCartItemRequest)

    @PutMapping("/remove-cart-item")
    fun removeCartItem(@RequestBody request: RemoveCartItemRequest)

    @PutMapping("/{userId}/empty")
    fun emptyCart(@PathVariable userId: UUID)
}