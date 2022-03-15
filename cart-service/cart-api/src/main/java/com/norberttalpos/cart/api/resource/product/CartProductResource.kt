package com.norberttalpos.cart.api.resource.product

import com.norberttalpos.product.api.dto.ProductDto
import org.springframework.web.bind.annotation.PathVariable

interface CartProductResource {
    fun getProductById(@PathVariable id: Long): ProductDto
}