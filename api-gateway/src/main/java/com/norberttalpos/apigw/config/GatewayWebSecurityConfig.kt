package com.norberttalpos.apigw.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class GatewayWebSecurityConfig {

    fun configure(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
                .authorizeExchange()
                .pathMatchers(
                    "/swagger**",
                    "/swagger-resources/**",
                    "/swagger-ui/**",
                    "/webjars/swagger-ui/**",
                    "/v3/api-docs**",
                    "/**/v3/api-docs"
                ).permitAll()
            .and()
                .authorizeExchange()
                .anyExchange()
                .authenticated()
            .and()
                .oauth2Login()

        return http.build()
    }
}