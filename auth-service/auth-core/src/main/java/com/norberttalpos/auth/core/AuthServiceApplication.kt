package com.norberttalpos.auth.core

import com.norberttalpos.auth.core.config.AppProperties
import com.norberttalpos.auth.core.model.entity.Role
import com.norberttalpos.auth.core.repository.RoleRepository
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
@EnableEurekaClient
@EnableTransactionManagement
@EnableFeignClients(
    basePackages = ["com.norberttalpos.customer.api.client"]
)
@OpenAPIDefinition(
    info = Info(title = "Auth API", version = "1.0", description = "Documentation Auth API v1.0")
)
class AuthServiceApplication {

    @Bean
    fun run(
        roleRepository: RoleRepository
    ) = CommandLineRunner {

        roleRepository.saveAll(
            listOf(
                Role().apply {
                    this.name = "user"
                },
                Role().apply {
                    this.name = "admin"
                },
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<AuthServiceApplication>(*args)
}