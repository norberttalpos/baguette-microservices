package com.norberttalpos.auth.core.repository

import com.norberttalpos.auth.core.model.entity.User
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : AbstractRepository<User> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}