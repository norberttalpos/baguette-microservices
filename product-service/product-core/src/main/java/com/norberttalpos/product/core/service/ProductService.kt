package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductFilter
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.core.entity.QProduct
import com.norberttalpos.product.core.repository.ProductRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service

@Service
class ProductService : AbstractDeletableService<Product, ProductFilter>() {

    override fun filter(filter: ProductFilter): Collection<Product> {
        val product: QProduct = QProduct.product
        val where = BooleanBuilder()

        filter.id?.let {
            where.and(product.id.eq(filter.id))
        }
        filter.brandName?.let {
            where.and(product.brand.name.containsIgnoreCase(filter.brandName))
        }
        filter.categoryName?.let {
            where.and(product.category.name.containsIgnoreCase(filter.categoryName))
        }

        return this.repository.findAll(where).toList()
    }

    override fun validateEntity(entity: Product): Boolean {
        return true
    }
}