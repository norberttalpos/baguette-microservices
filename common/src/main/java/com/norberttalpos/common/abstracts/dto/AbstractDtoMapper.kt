package com.norberttalpos.common.abstracts.dto

import com.norberttalpos.common.abstracts.entity.AbstractEntity

interface AbstractDtoMapper<ENTITY : AbstractEntity, DTO : AbstractDto> {

    fun toDto(entity: ENTITY): DTO

    fun fromDto(dto: DTO): ENTITY
}