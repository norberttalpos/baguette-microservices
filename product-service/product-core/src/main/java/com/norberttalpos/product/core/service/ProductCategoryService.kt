package com.norberttalpos.product.core.service

import com.norberttalpos.common.QueryBuilder
import com.norberttalpos.common.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductCategoryFilter
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.entity.QProductCategory
import org.springframework.stereotype.Service

@Service
class ProductCategoryService : AbstractDeletableService<ProductCategory, ProductCategoryFilter>() {

    override fun filter(filter: ProductCategoryFilter, whereMode: WhereMode): Collection<ProductCategory> {
        val productCategory: QProductCategory = QProductCategory.productCategory
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(productCategory.id.eq(filter.id))
        }
        filter.name?.let {
            where.addUniqueStringPred(productCategory.name, filter.name)
        }

        return this.repository.findAll(where.getBooleanBuilder()).toList()
    }

    override fun validateEntity(entity: ProductCategory): Boolean {
        val collisions = this.filter(ProductCategoryFilter(name = entity.name), WhereMode.OR)
        return collisions.isEmpty()
    }
}