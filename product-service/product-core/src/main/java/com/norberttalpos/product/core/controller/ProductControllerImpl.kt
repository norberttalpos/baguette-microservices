package com.norberttalpos.product.core.controller

import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import com.norberttalpos.product.api.controller.ProductController
import com.norberttalpos.product.api.controller.payload.ProductAmountChangeRequest
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.api.filter.ProductFilter
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.core.service.ProductService
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductControllerImpl : ProductController,
    AbstractDeletableControllerImpl<ProductDto, Product, ProductFilter, ProductService>() {

    override fun buyProduct(request: ProductAmountChangeRequest)
        = this.service.buyProduct(request.productName, request.amount)

    override fun addProductToStore(request: ProductAmountChangeRequest)
        = this.service.addProduct(request.productName, request.amount);
}