package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductCategoryFilter
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.entity.QProduct
import com.norberttalpos.product.core.entity.QProductCategory
import com.norberttalpos.product.core.repository.ProductCategoryRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service

@Service
class ProductCategoryService : AbstractDeletableService<ProductCategory, ProductCategoryFilter>() {

    override fun filter(filter: ProductCategoryFilter): Collection<ProductCategory> {
        val productCategory: QProductCategory = QProductCategory.productCategory
        val where = BooleanBuilder()

        filter.id?.let {
            where.and(productCategory.id.eq(filter.id))
        }

        return this.repository.findAll(where).toList()
    }

    override fun validateEntity(entity: ProductCategory): Boolean {
        return true
    }
}