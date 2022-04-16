package com.norberttalpos.order.core.security

import com.norberttalpos.common.security.resourceserver.AbstractResourceServerWebSecurityConfig
import com.norberttalpos.common.security.resourceserver.EndpointSecurityInfo
import org.springframework.http.HttpMethod

class OrderServiceWebSecurityConfig : AbstractResourceServerWebSecurityConfig() {

    override fun getEndpointSecurityInfo(): List<EndpointSecurityInfo> {
        return listOf(
            EndpointSecurityInfo("/order/customer-orders", HttpMethod.GET, mutableListOf(USER)),
            EndpointSecurityInfo("/order/**", HttpMethod.POST, mutableListOf(USER)),
            EndpointSecurityInfo("/order/**", HttpMethod.GET, mutableListOf(ADMIN)),
            )
    }
}