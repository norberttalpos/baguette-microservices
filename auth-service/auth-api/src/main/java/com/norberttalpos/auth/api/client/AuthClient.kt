package com.norberttalpos.auth.api.client

import com.norberttalpos.auth.api.dto.UserDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    value = "auth",
    url = "http://localhost:9000"
)
interface AuthClient {

    @GetMapping("/api/auth/user/me")
    fun getUser(@RequestHeader("Authorization") token: String): UserDto
}