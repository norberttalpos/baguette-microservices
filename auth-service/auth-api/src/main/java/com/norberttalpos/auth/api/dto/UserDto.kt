package com.norberttalpos.auth.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class UserDto(
    override var id: UUID?,
    var name: String?,
    var email: String?,
    var imageUrl: String?,
    var emailVerified: Boolean?,
    var roles: List<RoleDto>?,
) : AbstractDto