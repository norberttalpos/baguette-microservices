package com.norberttalpos.common.security

import com.norberttalpos.auth.api.client.AuthClient
import com.norberttalpos.auth.api.util.asSimpleGrantedAuthority
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ResourceServerJwtAuthFilter(
    private val authClient: AuthClient
) : OncePerRequestFilter() {

    private val logger = KotlinLogging.logger {}

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = getJwtFromRequest(request)

            if(jwt != null) {

                logger.info { "Found jwt $jwt in request"}

                val user = authClient.getUser(jwt)

                logger.info { "User ${user.email} retrieved via jwt"}

                val authentication =
                    UsernamePasswordAuthenticationToken(
                        user,
                        jwt,
                        user.roles!!.map { asSimpleGrantedAuthority(it.name!!) }
                    )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authentication

                logger.info { "Added security context authentication for user ${user.email}"}
            }

        } catch (ex: Exception) {
            logger.error { ex.message }
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }
}