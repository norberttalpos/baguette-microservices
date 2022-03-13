package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductBrandFilter
import com.norberttalpos.product.core.entity.ProductBrand
import com.norberttalpos.product.core.entity.QProductBrand
import com.norberttalpos.product.core.repository.ProductBrandRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Service

@Service
class ProductBrandService : AbstractDeletableService<ProductBrand, ProductBrandFilter>() {

    override fun filter(filter: ProductBrandFilter): Collection<ProductBrand> {
        val productBrand: QProductBrand = QProductBrand.productBrand
        val where = BooleanBuilder()

        filter.id?.let {
            where.and(productBrand.id.eq(filter.id))
        }

        return this.repository.findAll(where).toList()
    }

    override fun validateEntity(entity: ProductBrand): Boolean {
        return true
    }
}