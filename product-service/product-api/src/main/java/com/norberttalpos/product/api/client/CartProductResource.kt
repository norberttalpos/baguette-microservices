package com.norberttalpos.product.api.client

import com.norberttalpos.product.api.dto.ProductDto
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

interface CartProductResource {
    fun getProductById(@PathVariable id: Long): ProductDto?
}