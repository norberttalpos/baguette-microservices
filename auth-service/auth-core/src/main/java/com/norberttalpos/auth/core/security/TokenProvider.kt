package com.norberttalpos.auth.core.security

import com.norberttalpos.auth.core.config.AppProperties
import io.jsonwebtoken.*
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service
import java.security.SignatureException
import java.util.*


@Service
class TokenProvider(
    private val appProperties: AppProperties
) {

    private val logger = KotlinLogging.logger {}

    fun createToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserPrincipal
        val now = Date()
        val expiryDate = Date(now.time + appProperties.auth.tokenExpirationMsec)

        return Jwts.builder()
            .setSubject(userPrincipal.id.toString())
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .setClaims(
                mapOf(
                    Pair("roles", userPrincipal.authorities),
                    Pair("email", userPrincipal.email),
                    Pair("userid", userPrincipal.id),
                )
            )
            .signWith(SignatureAlgorithm.HS512, appProperties.auth.tokenSecret)
            .compact()
    }

    fun createToken(id: String, email: String, authorities: Collection<GrantedAuthority>): String {
        val now = Date()
        val expiryDate = Date(now.time + appProperties.auth.tokenExpirationMsec)

        return Jwts.builder()
            .setSubject(id)
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .setClaims(
                mapOf(
                    Pair("roles", authorities),
                    Pair("email", email),
                    Pair("userid", id),
                )
            )
            .signWith(SignatureAlgorithm.HS512, appProperties.auth.tokenSecret)
            .compact()
    }

    fun getUserIdFromToken(token: String?): UUID {
        val claims = Jwts.parserBuilder()
            .setSigningKey(appProperties.auth.tokenSecret)
            .build()
            .parseClaimsJws(token)
            .body

        return UUID.fromString(claims["userid"] as? String)
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
                    logger.error { "Invalid JWT signature" }
                }
                is MalformedJwtException -> {
                    logger.error { "Invalid JWT token" }
                }
                is ExpiredJwtException -> {
                    logger.error { "Expired JWT token" }
                }
                is UnsupportedJwtException -> {
                    logger.error { "Unsupported JWT token" }
                }
                is IllegalArgumentException -> {
                    logger.error { "JWT claims string is empty" }
                }
            }

            return false
        }
    }
}