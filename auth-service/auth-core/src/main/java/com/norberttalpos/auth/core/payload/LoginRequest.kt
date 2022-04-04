package com.norberttalpos.auth.core.payload

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class LoginRequest(
    val email: @NotBlank @Email String,
    val password: @NotBlank String
)