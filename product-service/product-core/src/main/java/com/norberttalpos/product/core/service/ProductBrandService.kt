package com.norberttalpos.product.core.service

import com.norberttalpos.common.QueryBuilder
import com.norberttalpos.common.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductBrandFilter
import com.norberttalpos.product.core.entity.ProductBrand
import com.norberttalpos.product.core.entity.QProductBrand
import org.springframework.stereotype.Service

@Service
class ProductBrandService : AbstractDeletableService<ProductBrand, ProductBrandFilter>() {

    override fun filter(filter: ProductBrandFilter, whereMode: WhereMode): Collection<ProductBrand> {
        val productBrand: QProductBrand = QProductBrand.productBrand
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(productBrand.id.eq(filter.id))
        }
        filter.name?.let {
            where.addUniqueStringPred(productBrand.name, filter.name)
        }

        return this.repository.findAll(where.getBooleanBuilder()).toList()
    }

    override fun validateEntity(entity: ProductBrand): Boolean {
        val collisions = this.filter(ProductBrandFilter(name = entity.name), WhereMode.OR)
        return collisions.isEmpty()
    }
}