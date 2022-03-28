package com.norberttalpos.auth.controller

import com.norberttalpos.auth.exception.ResourceNotFoundException
import com.norberttalpos.auth.model.User
import com.norberttalpos.auth.repository.UserRepository
import com.norberttalpos.auth.security.CurrentUser
import com.norberttalpos.auth.security.UserPrincipal
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(
    private val userRepository: UserRepository
) {

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    fun getCurrentUser(@CurrentUser userPrincipal: UserPrincipal): User {
        return userRepository.findById(userPrincipal.id)
            .orElseThrow {
                ResourceNotFoundException(
                    "User",
                    "id",
                    userPrincipal.id
                )
            }
    }
}