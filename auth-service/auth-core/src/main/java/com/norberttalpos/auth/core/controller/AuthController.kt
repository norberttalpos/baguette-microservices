package com.norberttalpos.auth.core.controller

import com.norberttalpos.auth.core.exception.BadRequestException
import com.norberttalpos.auth.core.model.AuthProvider
import com.norberttalpos.auth.core.model.User
import com.norberttalpos.auth.core.payload.ApiResponse
import com.norberttalpos.auth.core.payload.AuthResponse
import com.norberttalpos.auth.core.payload.LoginRequest
import com.norberttalpos.auth.core.payload.SignUpRequest
import com.norberttalpos.auth.core.repository.UserRepository
import com.norberttalpos.auth.core.security.TokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest): ResponseEntity<*> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.email,
                loginRequest.password,
            )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val token = tokenProvider.createToken(authentication)

        return ResponseEntity.ok(AuthResponse(token))
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: @Valid SignUpRequest): ResponseEntity<*> {
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw BadRequestException("Email address already in use.")
        }

        val user = User().apply {
            this.name = signUpRequest.name
            this.email = signUpRequest.email
            this.password = signUpRequest.password
            this.provider = AuthProvider.local
            this.password = passwordEncoder.encode(this.password)
        }

        val result = userRepository.save(user)

        val location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/user/me")
            .buildAndExpand(result.id).toUri()

        return ResponseEntity.created(location)
            .body(ApiResponse(true, "User registered successfully"))
    }
}