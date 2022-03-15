package com.norberttalpos.product.api.client

import com.norberttalpos.product.api.dto.ProductDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "product", url = "http://localhost:8080")
interface ProductClient : CartProductResource {

    @GetMapping("/api/product/product/{id}")
    override fun getProductById(@PathVariable id: Long): ProductDto?
}