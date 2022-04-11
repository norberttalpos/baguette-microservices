package com.norberttalpos.auth.core.repository

import com.norberttalpos.auth.core.model.entity.Role
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : AbstractRepository<Role> {
    fun findByName(name: String): Role?
}