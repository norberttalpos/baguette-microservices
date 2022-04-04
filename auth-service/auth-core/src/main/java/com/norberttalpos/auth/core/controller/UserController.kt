package com.norberttalpos.auth.core.controller

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.auth.core.exception.ResourceNotFoundException
import com.norberttalpos.auth.core.model.User
import com.norberttalpos.auth.core.model.UserMapper
import com.norberttalpos.auth.core.repository.UserRepository
import com.norberttalpos.auth.core.security.CurrentUser
import com.norberttalpos.auth.core.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    fun getCurrentUser(@CurrentUser userPrincipal: UserPrincipal): UserDto {

        val user = userRepository.findByIdOrNull(userPrincipal.id)

        if(user != null)
            return userMapper.toDto(user)
        else
            throw ResourceNotFoundException("User", "id", userPrincipal.id ?: "null")
    }
}