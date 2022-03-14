package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.controller.restobjects.ProductAmountChangeRequest
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.api.filter.ProductFilter
import org.springframework.web.bind.annotation.RequestBody

interface ProductController : AbstractDeletableController<ProductDto, ProductFilter> {

    fun buyProduct(@RequestBody request: ProductAmountChangeRequest);

    fun addProductToStore(@RequestBody request: ProductAmountChangeRequest);
}