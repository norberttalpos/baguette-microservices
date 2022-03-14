package com.norberttalpos.product.core.controller

import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import com.norberttalpos.product.api.controller.ProductController
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.api.filter.ProductFilter
import com.norberttalpos.product.api.controller.restobjects.ProductAmountChangeRequest
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.core.mapper.ProductMapper
import com.norberttalpos.product.core.service.ProductService
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductControllerImpl : ProductController,
    AbstractDeletableControllerImpl<ProductDto, Product, ProductFilter, ProductMapper, ProductService>() {

    @PutMapping("/buy")
    override fun buyProduct(request: ProductAmountChangeRequest) {
        this.changeProductAmount(request.productName, -request.amount);
    }

    @PutMapping("/add")
    override fun addProductToStore(request: ProductAmountChangeRequest) {
        this.changeProductAmount(request.productName, request.amount);
    }

    private fun changeProductAmount(productName: String, amount: Int) {
        this.service.filter(ProductFilter(name = productName)).forEach {
            it.amount += amount
            this.service.put(it)
        }
    }
}