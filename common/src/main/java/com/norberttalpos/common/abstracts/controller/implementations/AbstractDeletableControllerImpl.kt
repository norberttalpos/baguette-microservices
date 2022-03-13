package com.norberttalpos.common.abstracts.controller.implementations

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
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
        >
    : AbstractDeletableController<DTO, FILTER>, AbstractModifiableControllerImpl<DTO, ENTITY, FILTER, MAPPER, SERVICE>() {

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: Long): ResponseEntity<Unit> {
        this.service.deleteById(id)

        return ResponseEntity.ok(null)
    }
}