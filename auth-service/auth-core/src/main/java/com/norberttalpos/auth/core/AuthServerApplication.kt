package com.norberttalpos.auth.core

import com.norberttalpos.auth.core.config.AppProperties
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
@EnableEurekaClient
@OpenAPIDefinition(
    info = Info(title = "Auth API", version = "1.0", description = "Documentation Auth API v1.0")
)
class AuthServerApplication

fun main(args: Array<String>) {
    runApplication<AuthServerApplication>(*args)
}