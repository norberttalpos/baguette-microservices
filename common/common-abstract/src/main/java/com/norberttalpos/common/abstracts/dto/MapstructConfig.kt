package com.norberttalpos.common.abstracts.dto

import org.mapstruct.MapperConfig
import org.mapstruct.NullValuePropertyMappingStrategy

@MapperConfig(
    componentModel = "spring",
    //nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface MapstructConfig