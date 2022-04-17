package com.norberttalpos.common.security.resourceserver

import com.norberttalpos.auth.api.client.AuthClient
import com.norberttalpos.common.security.ResourceServerJwtAuthFilter
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

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

    private val logger = KotlinLogging.logger {}

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

        logger.info { "defined endpoint securitiy: $this.getEndpointSecurityInfo()" }

        http.csrf().disable()

        http.addFilterBefore(jwtToUserConverterFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    private fun jwtToUserConverterFilter(): ResourceServerJwtAuthFilter {
        return ResourceServerJwtAuthFilter(authClient)
    }

    abstract fun getEndpointSecurityInfo(): List<EndpointSecurityInfo>

    private fun convenientEndpointSecurityInfo(): List<EndpointSecurityInfo> {

        var list = this.getEndpointSecurityInfo().map {
            it.apply {
                if(it.requiredRoles.size > 0) {
                    val role = it.requiredRoles.first()

                    if(role == "ADMIN")
                        it.requiredRoles.add("USER")
                }
            }
        }

        val slashAdded = list.map { it.copy() }

        slashAdded.filter { it.route.endsWith("/**") }
            .map { it.route = it.route.substring(0, it.route.length - 3) }

        list = mutableListOf(*list.toTypedArray())
        list.addAll(slashAdded)

        return list
    }
}