package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.dto.ProductCategoryChildrenDto
import com.norberttalpos.product.api.dto.ProductCategoryDto
import com.norberttalpos.product.api.filter.ProductCategoryFilter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product-category")
@Tag(name = "Product Category")
interface ProductCategoryController : AbstractDeletableController<ProductCategoryDto, ProductCategoryFilter> {

    @GetMapping("/{name}/children")
    fun getProductCategoryChildren(@PathVariable name: String): ResponseEntity<ProductCategoryChildrenDto>

    @GetMapping("/{name}/to-root")
    fun getProductCategoryUpToRoot(@PathVariable name: String): ResponseEntity<List<ProductCategoryDto>>
}