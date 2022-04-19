package com.norberttalpos.product.core.controller

import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import com.norberttalpos.product.api.controller.ProductCategoryController
import com.norberttalpos.product.api.dto.ProductCategoryChildrenDto
import com.norberttalpos.product.api.dto.ProductCategoryDto
import com.norberttalpos.product.api.filter.ProductCategoryFilter
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.service.ProductCategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductCategoryControllerImpl : ProductCategoryController,
    AbstractDeletableControllerImpl<ProductCategoryDto, ProductCategory, ProductCategoryFilter, ProductCategoryService>() {

    override fun getProductCategoryChildren(name: String): ResponseEntity<ProductCategoryChildrenDto> {
        return ResponseEntity.ok(this.service.getProductCategoryChildren(name))
    }

    override fun getProductCategoryUpToRoot(name: String): ResponseEntity<List<ProductCategoryDto>> {
        return ResponseEntity.ok(
            this.service.getProductCategoryUpToRoot(name)
                .map { this.mapper.toDto(it) }
        )
    }
}