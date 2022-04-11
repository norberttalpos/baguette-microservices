package com.norberttalpos.auth.core.util

import com.norberttalpos.auth.core.model.entity.Role
import com.norberttalpos.auth.core.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleDeterminerService(
    private val roleRepository: RoleRepository,
) {

    fun determineRoles(email: String): List<Role> {
        val roles = mutableListOf<Role>()

        roles.add(this.roleRepository.findByName("user")!!)

        if(email.contains("norberttalpos")) {
            roles.add(this.roleRepository.findByName("admin")!!)
        }

        return roles
    }
}