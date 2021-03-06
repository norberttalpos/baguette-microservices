package com.norberttalpos.product.core.controller

import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import com.norberttalpos.product.api.controller.ProductBrandController
import com.norberttalpos.product.api.dto.ProductBrandDto
import com.norberttalpos.product.api.filter.ProductBrandFilter
import com.norberttalpos.product.core.entity.ProductBrand
import com.norberttalpos.product.core.service.ProductBrandService
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductBrandControllerImpl : ProductBrandController,
        AbstractDeletableControllerImpl<ProductBrandDto, ProductBrand, ProductBrandFilter, ProductBrandService>()