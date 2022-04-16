package com.norberttalpos.common.security

import com.norberttalpos.auth.api.client.AuthClient
import com.norberttalpos.auth.api.util.asSimpleGrantedAuthority
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtToUserConverterFilter(
    private val authClient: AuthClient
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = getJwtFromRequest(request)

            if(jwt != null) {
                val user = authClient.getUser(jwt)

                val authentication =
                    UsernamePasswordAuthenticationToken(
                        user,
                        jwt,
                        user.roles!!.map { asSimpleGrantedAuthority(it.name!!) }
                    )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authentication
            }

        } catch (ex: Exception) {
            println(ex.message)
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }
}