package com.norberttalpos.customer.core.security

import com.norberttalpos.common.security.resourceserver.AbstractResourceServerWebSecurityConfig
import com.norberttalpos.common.security.resourceserver.EndpointSecurityInfo
import org.springframework.http.HttpMethod

class CustomerServiceWebSecurityConfig : AbstractResourceServerWebSecurityConfig() {

    override fun getEndpointSecurityInfo(): List<EndpointSecurityInfo> {
        return listOf(
            EndpointSecurityInfo("/customer/**", HttpMethod.GET, mutableListOf()),
            EndpointSecurityInfo("/customer/**", HttpMethod.POST, mutableListOf()),
        )
    }
}