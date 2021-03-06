package com.norberttalpos.product.api.controller

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.auth.api.util.CurrentUser
import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.product.api.controller.payload.ProductAmountChangeRequest
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.api.filter.ProductFilter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
interface ProductController : AbstractDeletableController<ProductDto, ProductFilter> {

    @PutMapping("/buy")
    fun buyProduct(@RequestBody request: ProductAmountChangeRequest, @CurrentUser user: UserDto)

    @PutMapping("/add")
    fun addProductToStore(@RequestBody request: ProductAmountChangeRequest)
}