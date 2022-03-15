package com.norberttalpos.product.core.client

import com.norberttalpos.product.api.client.ProductResource
import com.norberttalpos.product.api.dto.ProductDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "product", url = "http://localhost:8080")
abstract class ProductClient : ProductResource {

    @GetMapping("/api/product/product/{id}")
    abstract override fun getProductById(@PathVariable id: Long): ProductDto
}