package com.norberttalpos.abstracts.controller.implementations

import com.norberttalpos.abstracts.controller.interfaces.AbstractModifiableController
import com.norberttalpos.abstracts.dto.AbstractDto
import com.norberttalpos.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.abstracts.entity.AbstractEntity
import com.norberttalpos.abstracts.filter.AbstractFilter
import com.norberttalpos.abstracts.repository.AbstractRepository
import com.norberttalpos.abstracts.service.AbstractModifiableService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class AbstractModifiableControllerImpl<
        DTO : AbstractDto,
        ENTITY: AbstractEntity,
        FILTER : AbstractFilter,
        MAPPER : AbstractDtoMapper<ENTITY, DTO>,
        SERVICE : AbstractModifiableService<ENTITY, FILTER, out AbstractRepository<ENTITY>>
        >(
    service: SERVICE,
    mapper: MAPPER
    )
    :
    AbstractModifiableController<DTO, FILTER>,
    AbstractGettableControllerImpl<DTO, ENTITY, FILTER, MAPPER, SERVICE>(service, mapper) {

    @PutMapping
    override fun put(@RequestBody dto: DTO): ResponseEntity<DTO> {
        val persistedEntity = this.service.put(this.mapper.fromDto(dto)) ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(this.mapper.toDto(persistedEntity))
    }

    @PostMapping
    override fun post(@RequestBody dto: DTO): ResponseEntity<DTO> {
        val persistedEntity = this.service.post(this.mapper.fromDto(dto)) ?: return ResponseEntity.badRequest().build()

        return ResponseEntity.ok(this.mapper.toDto(persistedEntity))
    }
}