package com.norberttalpos.auth.security

import com.norberttalpos.auth.config.AppProperties
import io.jsonwebtoken.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.security.SignatureException
import java.util.*


@Service
class TokenProvider(
    private val appProperties: AppProperties
) {
    fun createToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserPrincipal
        val now = Date()
        val expiryDate = Date(now.time + appProperties.auth.tokenExpirationMsec)

        return Jwts.builder()
            .setSubject(userPrincipal.id.toString())
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, appProperties.auth.tokenSecret)
            .compact()
    }

    fun getUserIdFromToken(token: String?): UUID {
        val claims = Jwts.parserBuilder()
            .setSigningKey(appProperties.auth.tokenSecret)
            .build()
            .parseClaimsJws(token)
            .body

        return UUID.fromString(claims.subject)
    }

    fun validateToken(authToken: String?): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(appProperties.auth.tokenSecret)
                .build()
                .parseClaimsJws(authToken)

            return true

        } catch (ex: Exception) {
            when(ex) {
                is SignatureException -> {
                    println("Invalid JWT signature")
                }
                is MalformedJwtException -> {
                    println("Invalid JWT token")
                }
                is ExpiredJwtException -> {
                    println("Expired JWT token")
                }
                is UnsupportedJwtException -> {
                    println("Unsupported JWT token")
                }
                is IllegalArgumentException -> {
                    println("JWT claims string is empty.")
                }
            }

            return false
        }
    }
}