package com.norberttalpos.abstracts.controller.interfaces

import com.norberttalpos.abstracts.dto.AbstractDto
import com.norberttalpos.abstracts.filter.AbstractFilter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface AbstractModifiableController<DTO : AbstractDto, FILTER : AbstractFilter>
    : AbstractGettableController<DTO, FILTER> {

    fun put(@RequestBody dto: DTO): ResponseEntity<DTO>

    fun post(@RequestBody dto: DTO): ResponseEntity<DTO>
}