package com.norberttalpos.common.security.resourceserver

import com.norberttalpos.auth.api.client.AuthClient
import com.norberttalpos.common.security.JwtToUserConverterFilter
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
abstract class AbstractResourceServerWebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var authClient: AuthClient

    companion object {
        const val USER = "USER"
        const val ADMIN = "ADMIN"
    }

    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
            .antMatchers("/v3/api-docs/**")
            .permitAll()

        this.convenientEndpointSecurityInfo().forEach {
            if(it.requiredRoles.size == 0) {
                http.authorizeRequests().antMatchers(it.method, it.route).permitAll()
            } else {
                http.authorizeRequests().antMatchers(it.method, it.route).hasAnyRole(*it.requiredRoles.toTypedArray())
            }
        }

        http
            .authorizeRequests()
            .anyRequest().hasAnyRole(ADMIN)

        println(this.getEndpointSecurityInfo())

        http.csrf().disable()

        http.cors()

        http.addFilterBefore(jwtToUserConverterFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun corsWebFilter(): CorsWebFilter {
        val corsConfiguration = CorsConfiguration().apply {
            this.allowCredentials = true
            this.addAllowedOriginPattern("*")
            this.addAllowedMethod("*")
            this.addAllowedOrigin("*")
            this.addExposedHeader(HttpHeaders.SET_COOKIE)
        }

        val corsConfigurationSource = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }

        return CorsWebFilter(corsConfigurationSource)
    }

    private fun jwtToUserConverterFilter(): JwtToUserConverterFilter {
        return JwtToUserConverterFilter(authClient)
    }

    abstract fun getEndpointSecurityInfo(): List<EndpointSecurityInfo>

    private fun convenientEndpointSecurityInfo(): List<EndpointSecurityInfo> {

        return this.getEndpointSecurityInfo().map {
            it.apply {
                if(it.requiredRoles.size > 0) {
                    val role = it.requiredRoles.first()

                    if(role == "ADMIN")
                        it.requiredRoles.add("USER")
                }
            }
        }
    }
}