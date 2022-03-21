package com.norberttalpos.product.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.controller.restobjects.ProductAmountChangeRequest
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.api.filter.ProductFilter
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
interface ProductController : AbstractDeletableController<ProductDto, ProductFilter> {

    @PutMapping("/buy")
    fun buyProduct(@RequestBody request: ProductAmountChangeRequest);

    @PutMapping("/add")
    fun addProductToStore(@RequestBody request: ProductAmountChangeRequest);
}