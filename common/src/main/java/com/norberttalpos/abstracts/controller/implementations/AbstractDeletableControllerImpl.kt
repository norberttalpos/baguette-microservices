package com.norberttalpos.abstracts.controller.implementations

import com.norberttalpos.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.abstracts.dto.AbstractDto
import com.norberttalpos.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.abstracts.entity.AbstractEntity
import com.norberttalpos.abstracts.filter.AbstractFilter
import com.norberttalpos.abstracts.repository.AbstractRepository
import com.norberttalpos.abstracts.service.AbstractDeletableService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class AbstractDeletableControllerImpl<
        DTO : AbstractDto,
        ENTITY: AbstractEntity,
        FILTER : AbstractFilter,
        MAPPER : AbstractDtoMapper<ENTITY, DTO>,
        SERVICE : AbstractDeletableService<ENTITY, FILTER, out AbstractRepository<ENTITY>>
        >(
    service: SERVICE,
    mapper: MAPPER
    ) : AbstractDeletableController<DTO, FILTER>, AbstractModifiableControllerImpl<DTO, ENTITY, FILTER, MAPPER, SERVICE>(
    service,
    mapper
) {

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        this.service.deleteById(id)

        return ResponseEntity.ok(null)
    }
}