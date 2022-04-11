package com.norberttalpos.auth.core.model.mapper

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.auth.core.model.entity.User
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
abstract class UserMapper : AbstractDtoMapper<User, UserDto>()