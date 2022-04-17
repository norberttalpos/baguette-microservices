package com.norberttalpos.cart.api.controller

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.auth.api.util.CurrentUser
import com.norberttalpos.cart.api.controller.payload.AddCartItemToCartRequest
import com.norberttalpos.cart.api.controller.payload.CreateCartRequest
import com.norberttalpos.cart.api.controller.payload.ModifyCartItemRequest
import com.norberttalpos.cart.api.controller.payload.RemoveCartItemRequest
import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.common.abstracts.controller.interfaces.AbstractGettableController
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart")
interface CartController : AbstractGettableController<CartDto, CartFilter> {

    @PostMapping
    fun create(@RequestBody createCartRequest: CreateCartRequest): ResponseEntity<UUID>

    @GetMapping("/current-cart")
    fun getCurrentCartOfCustomer(@CurrentUser currentUser: UserDto): ResponseEntity<CartDto>

    @PutMapping("/add-product-to-cart")
    fun addProductToCart(
        @RequestBody request: AddCartItemToCartRequest,
        @CurrentUser currentUser: UserDto
    ): ResponseEntity<CartDto>

    @PutMapping("/modify-cart-item")
    fun modifyCartItem(
        @RequestBody request: ModifyCartItemRequest,
        @CurrentUser currentUser: UserDto
    ): ResponseEntity<CartDto>

    @PutMapping("/remove-cart-item")
    fun removeCartItem(
        @RequestBody request: RemoveCartItemRequest,
        @CurrentUser currentUser: UserDto
    ): ResponseEntity<CartDto>

    @PutMapping("/empty")
    fun emptyCart(
        @CurrentUser currentUser: UserDto
    ): ResponseEntity<CartDto>

    @PutMapping("/create-order")
    fun createOrder(
        @CurrentUser currentUser: UserDto
    ): ResponseEntity<Unit>

    @DeleteMapping("/delete-customer-carts")
    fun deleteCustomerCarts(
        @CurrentUser currentUser: UserDto
    ): ResponseEntity<Unit>
}