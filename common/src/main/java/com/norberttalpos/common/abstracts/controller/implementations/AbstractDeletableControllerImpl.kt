package com.norberttalpos.common.abstracts.controller.implementations

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
abstract class AbstractDeletableControllerImpl<
        DTO : AbstractDto,
        ENTITY: AbstractEntity,
        FILTER : AbstractFilter,
        SERVICE : AbstractDeletableService<ENTITY, FILTER>
        >
    : AbstractDeletableController<DTO, FILTER>, AbstractModifiableControllerImpl<DTO, ENTITY, FILTER, SERVICE>() {

    @DeleteMapping("/{id}")
    override fun deleteById(@PathVariable id: UUID): ResponseEntity<Unit> {
        this.service.deleteById(id)

        return ResponseEntity.ok(null)
    }
}