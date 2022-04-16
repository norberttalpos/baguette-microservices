package com.norberttalpos.auth.api.client

import com.norberttalpos.auth.api.dto.UserDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import java.util.*
import javax.validation.constraints.Email

@FeignClient(
    value = "auth",
    url = "http://localhost:8080"
)
interface AuthClient {

    @GetMapping("/api/auth/user/me")
    fun getUser(@RequestHeader("Authorization") token: String): UserDto

    @GetMapping("/api/auth/user/{email}/exists")
    fun userExists(@PathVariable("email") email: @Email String): Boolean
}