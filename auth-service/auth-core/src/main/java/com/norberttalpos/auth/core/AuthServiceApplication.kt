package com.norberttalpos.auth.core

import com.norberttalpos.auth.core.config.AppProperties
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
@EnableEurekaClient
@EnableFeignClients(
    basePackages = ["com.norberttalpos.customer.api.client"]
)
@OpenAPIDefinition(
    info = Info(title = "Auth API", version = "1.0", description = "Documentation Auth API v1.0")
)
class AuthServiceApplication

fun main(args: Array<String>) {
    runApplication<AuthServiceApplication>(*args)
}