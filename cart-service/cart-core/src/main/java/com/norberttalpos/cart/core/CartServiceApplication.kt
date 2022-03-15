package com.norberttalpos.cart.core

import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.cart.core.entity.CartItem
import com.norberttalpos.cart.core.repository.CartItemRepository
import com.norberttalpos.cart.core.service.CartService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableFeignClients(
    basePackages = ["com.norberttalpos.product.api.client"]
)
class CartServiceApplication {

    @Bean
    fun run(
        cartService: CartService,
        cartItemRepository: CartItemRepository
    ) = CommandLineRunner {
        val cart = Cart().apply {
            this.userId = 1
        }

        cartService.post(cart)

        val cartItems = arrayListOf(
            CartItem().apply {
                this.productId = 1
                this.amount = 10
                this.cart = cart
            }
        )

        cartItemRepository.saveAll(cartItems)
    }
}

fun main(args: Array<String>) {
    runApplication<CartServiceApplication>(*args)
}