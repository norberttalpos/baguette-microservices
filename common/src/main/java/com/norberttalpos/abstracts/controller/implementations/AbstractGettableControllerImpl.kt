package com.norberttalpos.abstracts.controller.implementations

import com.norberttalpos.abstracts.controller.interfaces.AbstractGettableController
import com.norberttalpos.abstracts.dto.AbstractDto
import com.norberttalpos.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.abstracts.entity.AbstractEntity
import com.norberttalpos.abstracts.filter.AbstractFilter
import com.norberttalpos.abstracts.repository.AbstractRepository
import com.norberttalpos.abstracts.service.AbstractGettableService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
abstract class AbstractGettableControllerImpl<
        DTO : AbstractDto,
        ENTITY: AbstractEntity,
        FILTER : AbstractFilter,
        MAPPER : AbstractDtoMapper<ENTITY, DTO>,
        SERVICE : AbstractGettableService<ENTITY, FILTER, out AbstractRepository<ENTITY>>
        >(
    protected val service: SERVICE,
    protected val mapper: MAPPER
    )
    :
    AbstractGettableController<DTO, FILTER> {

    @GetMapping
    override fun getEntities(): ResponseEntity<Collection<DTO>> {
        val dtos = this.service.getEntities().map(this.mapper::toDto)

        return ResponseEntity.ok(dtos)
    }

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: Long): ResponseEntity<DTO> {
        val entity = this.service.getById(id) ?: return ResponseEntity.notFound().build()

        val dto = this.mapper.toDto(entity)

        return ResponseEntity.ok(dto)
    }

    @PostMapping("/filter")
    override fun filter(@RequestBody filter: FILTER): ResponseEntity<Collection<DTO>> {
        val dtos = this.service.filter(filter).map(this.mapper::toDto)

        return ResponseEntity.ok(dtos)
    }
}