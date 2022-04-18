package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
interface AbstractGettableController<DTO : AbstractDto, FILTER : AbstractFilter> {

    @GetMapping
    @Operation(description = "gets all entities")
    fun getEntities(@RequestHeader headers: HttpHeaders): ResponseEntity<List<DTO>>

    @GetMapping("/{id}")
    @Operation(description = "gets an entity by id")
    fun getById(@PathVariable id: Long): ResponseEntity<DTO>

    @PostMapping("/filter")
    @Operation(description = "gets the entities satisfying a provided filter")
    fun filter(@RequestBody filter: FILTER): ResponseEntity<List<DTO>>
}