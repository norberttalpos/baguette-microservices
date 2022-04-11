package com.norberttalpos.auth.api.util

import org.springframework.security.core.authority.SimpleGrantedAuthority

fun asSimpleGrantedAuthority(roleName: String): SimpleGrantedAuthority {
    return SimpleGrantedAuthority("ROLE_${roleName.uppercase()}")
}