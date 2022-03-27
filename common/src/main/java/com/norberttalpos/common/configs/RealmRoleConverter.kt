package com.norberttalpos.common.configs

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import java.util.stream.Collectors

class RealmRoleConverter : Converter<Jwt, Collection<GrantedAuthority>> {
    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val realmAccess = jwt.getClaim("realm_access") as Map<String, List<String>>
        return realmAccess["roles"]
            ?.stream()
            ?.map { roleName: String -> "ROLE_$roleName" }
            ?.map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
            ?.collect(Collectors.toList()) ?: emptyList()
    }
}