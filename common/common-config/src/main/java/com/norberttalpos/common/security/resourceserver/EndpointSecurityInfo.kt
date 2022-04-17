package com.norberttalpos.common.security.resourceserver

import org.springframework.http.HttpMethod

data class EndpointSecurityInfo(
    var route: String,
    val method: HttpMethod,
    val requiredRoles: MutableList<String>,
)