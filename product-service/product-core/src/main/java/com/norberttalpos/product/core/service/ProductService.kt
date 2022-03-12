package com.norberttalpos.product.core.service

import com.norberttalpos.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.api.filter.ProductFilter
import com.norberttalpos.product.core.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    repository: ProductRepository
) : AbstractDeletableService<Product, ProductFilter, ProductRepository>(repository) {

    override fun filter(filter: ProductFilter): Collection<Product> {
        TODO("Not yet implemented")
    }

    override fun validateEntity(entity: Product): Boolean {
        TODO("Not yet implemented")
    }
}