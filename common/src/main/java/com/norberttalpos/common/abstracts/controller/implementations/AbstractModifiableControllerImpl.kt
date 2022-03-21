package com.norberttalpos.common.abstracts.controller.implementations

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
abstract class AbstractModifiableControllerImpl<
        DTO : AbstractDto,
        ENTITY: AbstractEntity,
        FILTER : AbstractFilter,
        SERVICE : AbstractModifiableService<ENTITY, FILTER>
        >
    : AbstractModifiableController<DTO, FILTER>, AbstractGettableControllerImpl<DTO, ENTITY, FILTER, SERVICE>() {

    @PutMapping
    override fun put(@RequestBody dto: DTO): ResponseEntity<Any> {
        val persistedEntity = this.service.put(this.mapper.fromDto(dto))

        return ResponseEntity.ok(this.mapper.toDto(persistedEntity))
    }

    @PostMapping
    override fun post(@RequestBody dto: DTO): ResponseEntity<Any> {
        return try {
            dto.id = null
            val persistedEntity = this.service.post(this.mapper.fromDto(dto))

            ResponseEntity.ok(this.mapper.toDto(persistedEntity))
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message.toString())
        }
    }
}