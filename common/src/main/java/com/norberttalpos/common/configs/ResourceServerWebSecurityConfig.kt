package com.norberttalpos.common.configs

import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)*/
//class ResourceServerWebSecurityConfig : WebSecurityConfigurerAdapter() {
//    override fun configure(http: HttpSecurity) {
//        http
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .oauth2ResourceServer()
//            .jwt { jwt: OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer ->
//                jwt.jwtAuthenticationConverter(
//                    jwtAuthenticationConverter()
//                )
//            }
//
//        http.csrf().disable()
//    }
//
//    private fun jwtAuthenticationConverter(): Converter<Jwt, out AbstractAuthenticationToken> {
//        return JwtAuthenticationConverter().apply {
//            setJwtGrantedAuthoritiesConverter(RealmRoleConverter())
//        }
//    }
//}