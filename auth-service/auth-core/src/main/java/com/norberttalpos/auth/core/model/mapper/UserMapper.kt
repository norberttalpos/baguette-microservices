package com.norberttalpos.auth.core.model.mapper

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.auth.core.model.entity.User
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import org.mapstruct.Mapper

@Mapper(config = MapstructConfig::class)
abstract class UserMapper : AbstractDtoMapper<User, UserDto>()