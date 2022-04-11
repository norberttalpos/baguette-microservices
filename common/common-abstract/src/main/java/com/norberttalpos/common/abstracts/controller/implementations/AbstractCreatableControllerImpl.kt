package com.norberttalpos.common.abstracts.controller.implementations

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractCreatableController
import com.norberttalpos.common.abstracts.controller.interfaces.AbstractModifiableController
import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.common.abstracts.service.AbstractModifiableService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
abstract class AbstractCreatableControllerImpl<
        DTO : AbstractDto,
        ENTITY: AbstractEntity,
        FILTER : AbstractFilter,
        SERVICE : AbstractModifiableService<ENTITY, FILTER, out AbstractRepository<ENTITY>>
        >
    : AbstractCreatableController<DTO, FILTER>, AbstractGettableControllerImpl<DTO, ENTITY, FILTER, SERVICE>() {

    @PostMapping
    override fun post(@RequestBody dto: DTO): ResponseEntity<Any> {
        return try {
            dto.id = null
            val savedEntity = this.service.post(this.mapper.fromDto(dto))

            ResponseEntity.ok(this.mapper.toDto(savedEntity))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message.toString())
        }
    }
}