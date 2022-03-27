package com.norberttalpos.common.abstracts.controller.implementations

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractGettableController
import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.service.AbstractFilterableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.annotation.security.RolesAllowed

@RestController
abstract class AbstractGettableControllerImpl<
        DTO : AbstractDto,
        ENTITY: AbstractEntity,
        FILTER : AbstractFilter,
        SERVICE : AbstractFilterableService<ENTITY, FILTER>>
    : AbstractGettableController<DTO, FILTER> {

    @Autowired
    protected lateinit var service: SERVICE

    @Autowired
    protected lateinit var mapper: AbstractDtoMapper<ENTITY, DTO>

    override fun getEntities(): ResponseEntity<List<DTO>> {
        val dtos = this.service.getEntities().map(this.mapper::toDto)

        return ResponseEntity.ok(dtos)
    }

    override fun getById(id: UUID): ResponseEntity<DTO> {
        val entity = this.service.getById(id) ?: return ResponseEntity.notFound().build()
        val dto = this.mapper.toDto(entity)

        return ResponseEntity.ok(dto)
    }

    override fun filter(filter: FILTER): ResponseEntity<List<DTO>> {
        val dtos = this.service.filter(filter).map(this.mapper::toDto)

        return ResponseEntity.ok(dtos)
    }
}