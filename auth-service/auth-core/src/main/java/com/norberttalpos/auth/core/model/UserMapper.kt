package com.norberttalpos.auth.core.model

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
abstract class UserMapper : AbstractDtoMapper<User, UserDto>()