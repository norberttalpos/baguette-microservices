package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.filter.ProductFilter
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.core.entity.QProduct
import com.norberttalpos.product.core.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService : AbstractDeletableService<Product, ProductFilter, ProductRepository>() {

    override fun filter(filter: ProductFilter, whereMode: WhereMode): List<Product> {
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

        return this.repository.findAll(where.builder).toList()
    }

    override fun validateEntity(entity: Product) = true

    override fun provideUniquenessCheckFilter(entity: Product) = ProductFilter(name = entity.name)

    fun buyProduct(productName: String, amount: Int) = this.changeProductQuantity(productName, -amount)

    fun addProduct(productName: String, amount: Int) = this.changeProductQuantity(productName, amount)

    private fun changeProductQuantity(productName: String, amount: Int) {
        val product = this.filter(ProductFilter(name = productName)).first()

        product.apply {
            this.quantity += amount
        }

        this.put(product)
    }
}