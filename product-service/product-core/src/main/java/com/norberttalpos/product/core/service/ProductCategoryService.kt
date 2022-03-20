package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductCategoryFilter
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.entity.QProductCategory
import org.springframework.stereotype.Service

@Service
class ProductCategoryService : AbstractDeletableService<ProductCategory, ProductCategoryFilter>() {

    override fun filter(filter: ProductCategoryFilter, whereMode: WhereMode): List<ProductCategory> {
        val productCategory: QProductCategory = QProductCategory.productCategory
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(productCategory.id.eq(filter.id))
        }
        filter.name?.let {
            where.addUniqueStringPred(productCategory.name, filter.name)
        }

        return this.repository.findAll(where.getBuilder()).toList()
    }

    override fun validateEntity(entity: ProductCategory) = true

    override fun provideUniquenessCheckFilter(entity: ProductCategory) = ProductCategoryFilter(name = entity.name)
}