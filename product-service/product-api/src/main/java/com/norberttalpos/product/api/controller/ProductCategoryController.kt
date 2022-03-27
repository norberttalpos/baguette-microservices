package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.dto.ProductCategoryDto
import com.norberttalpos.product.api.filter.ProductCategoryFilter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product-category")
@Tag(name = "Product Category")
interface ProductCategoryController : AbstractDeletableController<ProductCategoryDto, ProductCategoryFilter>