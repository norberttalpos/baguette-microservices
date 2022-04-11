package com.norberttalpos.auth.core.controller

import com.norberttalpos.auth.core.exception.BadRequestException
import com.norberttalpos.auth.core.model.entity.AuthProvider
import com.norberttalpos.auth.core.model.entity.User
import com.norberttalpos.auth.core.payload.ApiResponse
import com.norberttalpos.auth.core.payload.AuthResponse
import com.norberttalpos.auth.core.payload.LoginRequest
import com.norberttalpos.auth.core.payload.SignUpRequest
import com.norberttalpos.auth.core.repository.UserRepository
import com.norberttalpos.auth.core.security.TokenProvider
import com.norberttalpos.auth.core.util.RoleDeterminerService
import com.norberttalpos.customer.api.client.CustomerClient
import com.norberttalpos.customer.api.dto.CustomerDto
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
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
    private val tokenProvider: TokenProvider,
    private val customerClient: CustomerClient,
    private val roleDeterminerService: RoleDeterminerService,
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
            this.email = signUpRequest.email
            this.provider = AuthProvider.local
            this.password = passwordEncoder.encode(this.password)
            this.roles = roleDeterminerService.determineRoles(signUpRequest.email)
        }

        val result = userRepository.saveAndFlush(user)

        this.customerClient.registerCustomer(
            CustomerDto(
                id = result.id,
                name = signUpRequest.name,
                email = signUpRequest.email,
                phoneNumber = null, // TODO
                imageUrl = null,
                address = null
            )
        )

        val location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/user/me")
            .buildAndExpand(result.id).toUri()

        return ResponseEntity.created(location)
            .body(ApiResponse(true, "User registered successfully"))
    }
}