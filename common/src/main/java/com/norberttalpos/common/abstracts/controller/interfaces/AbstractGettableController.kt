package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
interface AbstractGettableController<DTO : AbstractDto, FILTER : AbstractFilter> {

    @GetMapping
    fun getEntities(): ResponseEntity<List<DTO>>

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<DTO>

    @PostMapping("/filter")
    fun filter(@RequestBody filter: FILTER): ResponseEntity<List<DTO>>
}