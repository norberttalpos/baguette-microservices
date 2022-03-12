package com.norberttalpos.abstracts.dto

import com.norberttalpos.abstracts.entity.AbstractEntity

interface AbstractDtoMapper<ENTITY : AbstractEntity, DTO : AbstractDto> {

    fun toDto(entity: ENTITY): DTO

    fun fromDto(dto: DTO): ENTITY
}