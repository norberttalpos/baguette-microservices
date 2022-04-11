package com.norberttalpos.customer.core

import com.norberttalpos.customer.core.security.CustomerServiceWebSecurityConfig
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
    basePackages = [ "com.norberttalpos.customer.core.entity"]
)
@EnableFeignClients(
    basePackages = ["com.norberttalpos.auth.api.client"]
)
@Import(
    CustomerServiceWebSecurityConfig::class
)
@OpenAPIDefinition(
    info = Info(title = "Customer API", version = "1.0", description = "Documentation Customer API v1.0")
)
class CustomerServiceApplication

fun main(args: Array<String>) {
    runApplication<CustomerServiceApplication>(*args)
}