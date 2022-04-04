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
abstract class AbstractModifiableControllerImpl<
        DTO : AbstractDto,
        ENTITY: AbstractEntity,
        FILTER : AbstractFilter,
        SERVICE : AbstractModifiableService<ENTITY, FILTER>
        >
    : AbstractModifiableController<DTO, FILTER>, AbstractCreatableControllerImpl<DTO, ENTITY, FILTER, SERVICE>() {

    @PutMapping
    override fun put(@RequestBody dto: DTO): ResponseEntity<Any> {
        return try {
            val dtoId = dto.id

            if(dtoId == null) ResponseEntity.badRequest().body("provide an id for the request dto")
            else {
                val persistedEntity = this.service.getById(dtoId)

                if(persistedEntity == null) ResponseEntity.badRequest().body("entity not found")
                else {
                    this.mapper.updateFromDto(dto, persistedEntity)
                    val modifiedEntity = this.service.put(persistedEntity)

                    return ResponseEntity.ok(this.mapper.toDto(modifiedEntity))
                }
            }
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message.toString())
        }
    }
}