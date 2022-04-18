package com.norberttalpos.auth.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class UserDto(
    override var id: Long?,
    var name: String?,
    var email: String?,
    var roles: List<RoleDto>?,
) : AbstractDto