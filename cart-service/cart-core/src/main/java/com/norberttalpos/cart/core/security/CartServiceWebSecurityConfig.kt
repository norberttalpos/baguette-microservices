package com.norberttalpos.cart.core.security

import com.norberttalpos.common.security.resourceserver.AbstractResourceServerWebSecurityConfig
import com.norberttalpos.common.security.resourceserver.EndpointSecurityInfo
import org.springframework.http.HttpMethod

class CartServiceWebSecurityConfig : AbstractResourceServerWebSecurityConfig() {

    override fun getEndpointSecurityInfo(): List<EndpointSecurityInfo> {
        return listOf(
            EndpointSecurityInfo("/cart/**", HttpMethod.POST, mutableListOf()),
            EndpointSecurityInfo("/cart/**", HttpMethod.GET, mutableListOf()),
            EndpointSecurityInfo("/cart/**", HttpMethod.PUT, mutableListOf()),
            EndpointSecurityInfo("/cart/**", HttpMethod.DELETE, mutableListOf()),
        )
    }
}