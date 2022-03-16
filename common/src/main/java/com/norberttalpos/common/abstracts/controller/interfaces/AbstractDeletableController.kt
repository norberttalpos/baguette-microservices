package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import org.springframework.http.ResponseEntity
import java.util.*

interface AbstractDeletableController<DTO : AbstractDto, FILTER : AbstractFilter>
    : AbstractModifiableController<DTO, FILTER> {

    fun deleteById(id: UUID): ResponseEntity<Unit>
}