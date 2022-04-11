package com.norberttalpos.cart.core.security

import com.norberttalpos.common.security.resourceserver.AbstractResourceServerWebSecurityConfig
import com.norberttalpos.common.security.resourceserver.EndpointSecurityInfo
import org.springframework.http.HttpMethod

class CartServiceWebSecurityConfig : AbstractResourceServerWebSecurityConfig() {

    override fun getEndpointSecurityInfo(): List<EndpointSecurityInfo> {
        return listOf(
            EndpointSecurityInfo("/cart/**", HttpMethod.GET, mutableListOf("USER")),
            EndpointSecurityInfo("/cart/**", HttpMethod.PUT, mutableListOf("USER")),
            EndpointSecurityInfo("/cart/**", HttpMethod.POST, mutableListOf("USER")),
            EndpointSecurityInfo("/cart/**", HttpMethod.DELETE, mutableListOf("USER")),
        )
    }
}