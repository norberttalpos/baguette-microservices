package com.norberttalpos.auth.core.payload

data class AuthResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)