package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface AbstractModifiableController<DTO : AbstractDto, FILTER : AbstractFilter>
    : AbstractGettableController<DTO, FILTER> {

    fun put(dto: DTO): ResponseEntity<Any>

    fun post(dto: DTO): ResponseEntity<Any>
}