package com.norberttalpos.apigw.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer


@Configuration
class CorsConfig : WebFluxConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedOrigins("*")
            .allowedHeaders("*")
            .allowedMethods("*")
            .exposedHeaders(HttpHeaders.SET_COOKIE);
    }

    @Bean
    fun corsWebFilter(): CorsWebFilter? {
        val corsConfiguration = CorsConfiguration().apply {
            this.allowCredentials = true
            this.addAllowedHeader("*")
            this.addAllowedMethod("*")
            this.addAllowedOrigin("*")
            this.addExposedHeader(HttpHeaders.SET_COOKIE)
        }

        val corsConfigurationSource = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }

        return CorsWebFilter(corsConfigurationSource)
    }
}