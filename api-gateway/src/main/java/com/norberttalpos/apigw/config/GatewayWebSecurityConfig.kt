package com.norberttalpos.apigw.config

import org.springframework.context.annotation.Configuration
/*import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain*/

//@Configuration
//@EnableWebFluxSecurity
//class GatewayWebSecurityConfig {
//
//    fun configure(httpSecurity: ServerHttpSecurity): SecurityWebFilterChain {
//        httpSecurity
//            .authorizeExchange()
//            .pathMatchers(
//                "/swagger**",
//                "/swagger-resources/**",
//                "/swagger-ui/**",
//                "/webjars/swagger-ui/**",
//                "/v3/api-docs**",
//                "/**/v3/api-docs"
//            )
//            .permitAll()
//
//        httpSecurity
//            .authorizeExchange()
//            .anyExchange().authenticated()
//            .and()
//            .cors()
//            .and()
//            .csrf()
//            .disable()
//            .oauth2ResourceServer()
//            .jwt()
//
//        return httpSecurity.build()
//    }
//}