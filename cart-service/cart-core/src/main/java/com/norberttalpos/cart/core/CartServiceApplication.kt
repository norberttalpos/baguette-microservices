package com.norberttalpos.cart.core

import com.norberttalpos.cart.core.security.CartServiceWebSecurityConfig
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
    basePackages = [
        "com.norberttalpos.product.api.client",
        "com.norberttalpos.auth.api.client",
        "com.norberttalpos.customer.api.client",
        "com.norberttalpos.order.api.client",
    ]
)
@Import(
    CartServiceWebSecurityConfig::class
)
@OpenAPIDefinition(
    info = Info(title = "Cart API", version = "1.0", description = "Documentation Cart API v1.0")
)
class CartServiceApplication

fun main(args: Array<String>) {
    runApplication<CartServiceApplication>(*args)
}