package com.norberttalpos.abstracts.controller.interfaces

import com.norberttalpos.abstracts.dto.AbstractDto
import com.norberttalpos.abstracts.filter.AbstractFilter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface AbstractGettableController<DTO : AbstractDto, FILTER : AbstractFilter> {

    fun getEntities(): ResponseEntity<Collection<DTO>>

    fun getById(@PathVariable id: Long): ResponseEntity<DTO>

    fun filter(@RequestBody filter: FILTER): ResponseEntity<Collection<DTO>>
}