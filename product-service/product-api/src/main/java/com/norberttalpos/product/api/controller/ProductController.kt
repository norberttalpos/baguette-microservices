package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.api.filter.ProductFilter
import org.springframework.web.bind.annotation.GetMapping

interface ProductController : AbstractDeletableController<ProductDto, ProductFilter>