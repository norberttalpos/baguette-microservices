package com.norberttalpos.product.core

import com.norberttalpos.common.feign.FeignConfiguration
import com.norberttalpos.product.core.security.ProductServiceWebSecurityConfig
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
    basePackages = [ "com.norberttalpos.product.core.entity" ]
)
@EnableFeignClients(
    basePackages = ["com.norberttalpos.auth.api.client"]
)
@Import(
    ProductServiceWebSecurityConfig::class,
    FeignConfiguration::class
)
@OpenAPIDefinition(
    info = Info(title = "Product API", version = "1.0", description = "Documentation Product API v1.0")
)
class ProductServiceApplication

fun main(args: Array<String>) {
    runApplication<ProductServiceApplication>(*args)
}