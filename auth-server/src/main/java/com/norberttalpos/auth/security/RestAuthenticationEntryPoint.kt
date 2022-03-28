package com.norberttalpos.auth.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class RestAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
        e: AuthenticationException
    ) {
        httpServletResponse.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            e.localizedMessage
        )
    }
}