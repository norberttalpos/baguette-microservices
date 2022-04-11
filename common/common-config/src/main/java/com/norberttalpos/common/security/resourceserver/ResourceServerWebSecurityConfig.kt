package com.norberttalpos.common.security.resourceserver

import com.norberttalpos.auth.api.client.AuthClient
import com.norberttalpos.common.security.JwtToUserConverterFilter
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
abstract class AbstractResourceServerWebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var authClient: AuthClient

    companion object {
        const val USER = "USER"
        const val ADMIN = "ADMIN"
    }

    override fun configure(http: HttpSecurity) {

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

        http.addFilterBefore(jwtToUserConverterFilter(), UsernamePasswordAuthenticationFilter::class.java)
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