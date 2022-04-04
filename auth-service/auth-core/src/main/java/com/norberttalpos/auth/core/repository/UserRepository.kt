package com.norberttalpos.auth.core.repository

import com.norberttalpos.auth.core.model.User
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : AbstractRepository<User> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}