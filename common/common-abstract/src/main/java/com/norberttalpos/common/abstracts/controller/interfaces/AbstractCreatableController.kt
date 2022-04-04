package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
interface AbstractCreatableController<DTO : AbstractDto, FILTER : AbstractFilter>
    : AbstractGettableController<DTO, FILTER> {

    @PostMapping
    @Operation(description = "creates a new entity")
    fun post(@RequestBody dto: DTO): ResponseEntity<Any>
}