package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

interface AbstractDeletableController<DTO : AbstractDto, FILTER : AbstractFilter>
    : AbstractModifiableController<DTO, FILTER> {

    fun deleteById(@PathVariable id: Long): ResponseEntity<Unit>
}