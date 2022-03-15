package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface AbstractGettableController<DTO : AbstractDto, FILTER : AbstractFilter> {

    fun getEntities(): ResponseEntity<List<DTO>>

    fun getById(id: Long): ResponseEntity<DTO>

    fun filter(filter: FILTER): ResponseEntity<List<DTO>>
}