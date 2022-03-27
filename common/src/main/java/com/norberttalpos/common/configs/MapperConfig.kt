package com.norberttalpos.common.configs

import org.mapstruct.MapperConfig
import org.mapstruct.NullValuePropertyMappingStrategy

@MapperConfig(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface MapperSpringConfig