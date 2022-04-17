package com.norberttalpos.auth.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class UserDto(
    override var id: UUID? = null,
    var name: String? = null,
    var email: String? = null,
    var roles: List<RoleDto>? = null,
) : AbstractDto