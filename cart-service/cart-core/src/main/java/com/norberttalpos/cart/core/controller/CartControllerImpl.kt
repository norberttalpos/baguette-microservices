package com.norberttalpos.cart.core.controller

import com.norberttalpos.cart.api.controller.CartController
import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.cart.core.mapper.CartMapper
import com.norberttalpos.cart.core.service.CartService
import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cart")
class CartControllerImpl : CartController,
    AbstractDeletableControllerImpl<CartDto, Cart, CartFilter, CartMapper, CartService>() {
}