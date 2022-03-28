package com.norberttalpos.auth.payload

data class AuthResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)