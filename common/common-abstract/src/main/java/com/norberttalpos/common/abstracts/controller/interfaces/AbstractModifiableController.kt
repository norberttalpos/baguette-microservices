package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
interface AbstractModifiableController<DTO : AbstractDto, FILTER : AbstractFilter>
    : AbstractCreatableController<DTO, FILTER> {

    @PutMapping
    @Operation(description = "modifies an entity")
    fun put(@RequestBody dto: DTO): ResponseEntity<Any>
}