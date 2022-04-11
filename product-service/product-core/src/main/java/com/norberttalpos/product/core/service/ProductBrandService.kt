package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductBrandFilter
import com.norberttalpos.product.core.entity.ProductBrand
import com.norberttalpos.product.core.entity.QProductBrand
import com.norberttalpos.product.core.repository.ProductBrandRepository
import org.springframework.stereotype.Service

@Service
class ProductBrandService : AbstractDeletableService<ProductBrand, ProductBrandFilter, ProductBrandRepository>() {

    override fun filter(filter: ProductBrandFilter, whereMode: WhereMode): List<ProductBrand> {
        val productBrand: QProductBrand = QProductBrand.productBrand
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(productBrand.id.eq(filter.id))
        }
        filter.name?.let {
            where.addUniqueStringPred(productBrand.name, filter.name)
        }

        return this.repository.findAll(where.getBuilder()).toList()
    }

    override fun validateEntity(entity: ProductBrand) = true

    override fun provideUniquenessCheckFilter(entity: ProductBrand) = ProductBrandFilter(name = entity.name)
}