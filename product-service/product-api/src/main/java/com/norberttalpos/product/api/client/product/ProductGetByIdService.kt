package com.norberttalpos.product.api.client.product

import com.norberttalpos.product.api.dto.ProductDto
import org.springframework.web.bind.annotation.PathVariable

interface ProductGetByIdService {
    fun getProductById(@PathVariable id: Long): ProductDto
}