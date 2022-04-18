package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
interface AbstractDeletableController<DTO : AbstractDto, FILTER : AbstractFilter>
    : AbstractModifiableController<DTO, FILTER> {

    @DeleteMapping("/{id}")
    @Operation(description = "deletes an entity")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit>
}