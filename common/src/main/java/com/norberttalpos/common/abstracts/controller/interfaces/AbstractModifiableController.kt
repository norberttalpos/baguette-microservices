package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
interface AbstractModifiableController<DTO : AbstractDto, FILTER : AbstractFilter>
    : AbstractCreatableController<DTO, FILTER> {

    @PutMapping
    fun put(@RequestBody dto: DTO): ResponseEntity<Any>
}