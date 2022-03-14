package com.norberttalpos.product.core.service

import com.norberttalpos.common.QueryBuilder
import com.norberttalpos.common.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductFilter
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.core.entity.QProduct
import org.springframework.stereotype.Service

@Service
class ProductService : AbstractDeletableService<Product, ProductFilter>() {

    override fun filter(filter: ProductFilter, whereMode: WhereMode): Collection<Product> {
        val product: QProduct = QProduct.product
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(product.id.eq(filter.id))
        }
        filter.name?.let {
            where.addUniqueStringPred(product.name, filter.name)
        }
        filter.brandName?.let {
            where.add(product.brand.name.containsIgnoreCase(filter.brandName))
        }
        filter.categoryName?.let {
            where.add(product.category.name.containsIgnoreCase(filter.categoryName))
        }

        return this.repository.findAll(where.getBooleanBuilder()).toList()
    }

    override fun validateEntity(entity: Product): Boolean {
        val collisions = this.filter(ProductFilter(name = entity.name), WhereMode.OR)
        return collisions.isEmpty()
    }
}