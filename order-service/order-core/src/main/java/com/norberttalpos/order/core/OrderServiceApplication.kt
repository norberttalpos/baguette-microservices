package com.norberttalpos.order.core

import com.norberttalpos.common.feign.FeignConfiguration
import com.norberttalpos.order.core.security.OrderServiceWebSecurityConfig
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import

@SpringBootApplication
@EnableEurekaClient
@EntityScan(
    basePackages = [ "com.norberttalpos.order.core.entity" ]
)
@EnableFeignClients(
    basePackages = [
        "com.norberttalpos.auth.api.client",
        "com.norberttalpos.customer.api.client",
    ]
)
@Import(
    OrderServiceWebSecurityConfig::class,
    FeignConfiguration::class
)
@OpenAPIDefinition(
    info = Info(title = "Order API", version = "1.0", description = "Order Product API v1.0")
)class OrderServiceApplication

fun main(args: Array<String>) {
    runApplication<OrderServiceApplication>(*args)
}