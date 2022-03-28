package com.norberttalpos.auth.payload

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignUpRequest(
    val name: @NotBlank String,
    val email: @NotBlank @Email String,
    val password: @NotBlank String,
)