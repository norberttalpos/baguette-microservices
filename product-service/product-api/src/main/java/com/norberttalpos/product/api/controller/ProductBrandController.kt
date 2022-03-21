package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.dto.ProductBrandDto
import com.norberttalpos.product.api.filter.ProductBrandFilter
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product-brand")
interface ProductBrandController : AbstractDeletableController<ProductBrandDto, ProductBrandFilter>