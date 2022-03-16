package com.norberttalpos.product.core.controller

import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import com.norberttalpos.product.api.controller.ProductCategoryController
import com.norberttalpos.product.api.dto.ProductCategoryDto
import com.norberttalpos.product.api.filter.ProductCategoryFilter
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.mapper.ProductCategoryMapper
import com.norberttalpos.product.core.service.ProductCategoryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product-category")
class ProductCategoryControllerImpl : ProductCategoryController,
    AbstractDeletableControllerImpl<ProductCategoryDto, ProductCategory, ProductCategoryFilter, ProductCategoryMapper, ProductCategoryService>()