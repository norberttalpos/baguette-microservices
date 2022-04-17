package com.norberttalpos.auth.api.dto

import com.norberttalpos.common.abstracts.dto.AbstractDto
import java.util.*

data class RoleDto(
    override var id: UUID? = null,
    var name: String? = null,
) : AbstractDto