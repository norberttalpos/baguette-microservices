package com.norberttalpos.auth.core.controller

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.auth.api.util.CurrentUser
import com.norberttalpos.auth.core.exception.ResourceNotFoundException
import com.norberttalpos.auth.core.model.mapper.UserMapper
import com.norberttalpos.auth.core.repository.UserRepository
import com.norberttalpos.auth.core.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.constraints.Email


@RestController
class UserController(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) {

    @GetMapping("/user/me")
    fun getCurrentUser(@CurrentUser userPrincipal: UserPrincipal): UserDto {

        val user = userRepository.findByIdOrNull(userPrincipal.id)

        if(user != null)
            return userMapper.toDto(user)
        else
            throw ResourceNotFoundException("User", "id", userPrincipal.id ?: "null")
    }

    @GetMapping("/user/{email}/exists")
    fun getUserExistsById(@PathVariable email: @Email String): Boolean {
        return userRepository.findByEmail(email) != null
    }

    @DeleteMapping("/user")
    fun deleteUser(@CurrentUser userPrincipal: UserPrincipal) {
        this.userRepository.deleteById(userPrincipal.id!!)
    }
}