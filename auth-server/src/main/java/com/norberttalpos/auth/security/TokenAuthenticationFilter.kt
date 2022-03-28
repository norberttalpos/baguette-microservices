package com.norberttalpos.auth.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class TokenAuthenticationFilter() : OncePerRequestFilter() {
    @Autowired
    private lateinit var tokenProvider: TokenProvider

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = getJwtFromRequest(request)

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                val userId = tokenProvider.getUserIdFromToken(jwt)
                val userDetails = customUserDetailsService.loadUserById(userId)

                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (ex: Exception) {
            // nop
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return listOf("auth/signup").stream().anyMatch { url -> AntPathRequestMatcher(url).matches(request) }
    }
}