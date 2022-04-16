package com.norberttalpos.product.core.security

import com.norberttalpos.common.security.resourceserver.AbstractResourceServerWebSecurityConfig
import com.norberttalpos.common.security.resourceserver.EndpointSecurityInfo
import org.springframework.http.HttpMethod

class ProductServiceWebSecurityConfig : AbstractResourceServerWebSecurityConfig() {

    override fun getEndpointSecurityInfo(): List<EndpointSecurityInfo> {
        return listOf(
            EndpointSecurityInfo("/product/buy/**", HttpMethod.PUT, mutableListOf(USER)),

            EndpointSecurityInfo("/product/**", HttpMethod.GET, mutableListOf()),
            EndpointSecurityInfo("/product-brand/**", HttpMethod.GET, mutableListOf()),
            EndpointSecurityInfo("/product-category/**", HttpMethod.GET, mutableListOf()),
            EndpointSecurityInfo("/measurement-unit/**", HttpMethod.GET, mutableListOf()),

            EndpointSecurityInfo("/product/filter", HttpMethod.POST, mutableListOf()),
            EndpointSecurityInfo("/product-brand/filter", HttpMethod.POST, mutableListOf()),
            EndpointSecurityInfo("/product-category/filter", HttpMethod.POST, mutableListOf()),
            EndpointSecurityInfo("/measurement-unit/filter", HttpMethod.POST, mutableListOf()),
            )
    }
}