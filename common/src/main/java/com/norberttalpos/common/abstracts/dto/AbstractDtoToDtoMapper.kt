package com.norberttalpos.common.abstracts.dto

abstract class AbstractDtoToDtoMapper<DTO1 : AbstractDto, DTO2 : AbstractDto> {

    abstract fun toDto2(dtO1: DTO1): DTO2

    abstract fun toDto1(dtO2: DTO2): DTO1
}