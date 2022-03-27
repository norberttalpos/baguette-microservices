package com.norberttalpos.common.abstracts.controller.interfaces

import com.norberttalpos.common.abstracts.dto.AbstractDto
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.annotation.security.RolesAllowed

@RestController
interface AbstractGettableController<DTO : AbstractDto, FILTER : AbstractFilter> {

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(description = "gets all entities")
    fun getEntities(): ResponseEntity<List<DTO>>

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(description = "gets an entity by id")
    fun getById(@PathVariable id: UUID): ResponseEntity<DTO>

    @PostMapping("/filter")
    @Operation(description = "gets the entities satisfying a provided filter")
    fun filter(@RequestBody filter: FILTER): ResponseEntity<List<DTO>>
}