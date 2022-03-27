package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.dto.ProductBrandDto
import com.norberttalpos.product.api.filter.ProductBrandFilter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product-brand")
@Tag(name = "Product Brand")
interface ProductBrandController : AbstractDeletableController<ProductBrandDto, ProductBrandFilter>