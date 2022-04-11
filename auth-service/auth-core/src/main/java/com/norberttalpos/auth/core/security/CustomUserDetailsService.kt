package com.norberttalpos.auth.core.security

import com.norberttalpos.auth.core.exception.ResourceNotFoundException
import com.norberttalpos.auth.core.model.entity.User
import com.norberttalpos.auth.core.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(email: String): UserDetails {
        val user: User = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("User not found with email : $email")

        return UserPrincipal.create(user)
    }

    @Transactional
    fun loadUserById(id: UUID): UserDetails {
        val user: User = userRepository.findByIdOrNull(id)
            ?: throw ResourceNotFoundException("User", "id", id)

        return UserPrincipal.create(user)
    }
}