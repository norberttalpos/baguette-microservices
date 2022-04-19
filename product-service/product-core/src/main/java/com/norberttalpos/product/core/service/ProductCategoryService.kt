package com.norberttalpos.product.core.service

import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.product.api.dto.ProductCategoryChildrenDto
import com.norberttalpos.product.api.filter.ProductCategoryFilter
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.entity.QProductCategory
import com.norberttalpos.product.core.repository.ProductCategoryRepository
import org.springframework.stereotype.Service

@Service
class ProductCategoryService
    : AbstractDeletableService<ProductCategory, ProductCategoryFilter, ProductCategoryRepository>() {

    override fun filter(filter: ProductCategoryFilter, whereMode: WhereMode): List<ProductCategory> {
        val productCategory: QProductCategory = QProductCategory.productCategory
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(productCategory.id.eq(filter.id))
        }
        filter.name?.let {
            where.addUniqueStringPred(productCategory.name, filter.name)
        }

        return this.repository.findAll(where.builder).toList()
    }

    override fun validateEntity(entity: ProductCategory) = true

    override fun provideUniquenessCheckFilter(entity: ProductCategory) = ProductCategoryFilter(name = entity.name)

    fun getProductCategoryChildren(name: String): ProductCategoryChildrenDto {
        val productCategories = this.getEntities()
        val r = productCategories.first { it.name == "grocery" }

        val root = ProductCategoryChildrenDto(id = r.id, name = r.name, children = mutableListOf())

        val processedNodes = mutableMapOf(Pair(root.id!!,root))

        var newAdded = true
        while(newAdded) {
            newAdded = false

            productCategories.forEach {
                if(processedNodes.keys.contains(it.parent) && !processedNodes.keys.contains(it.id)) {
                    val newElement = ProductCategoryChildrenDto(id = it.id, name = it.name, children = mutableListOf())
                    processedNodes[it.id!!] = newElement
                    processedNodes[it.parent]?.children?.plusAssign(newElement)

                    newAdded = true
                }
            }
        }

        return root
    }

    fun getAncestorsOfCategory(name: String): List<ProductCategory> {
        val productCategories = this.getEntities().asReversed()

        val child: ProductCategory
        try {
            child = productCategories.first { it.name == name }
        } catch (e: NoSuchElementException) {
            throw IllegalArgumentException("No product category with name $name found")
        }

        val root = productCategories.first { it.name == "grocery" }

        val list = mutableListOf(child)

        var currentName = child.name
        while(currentName != root.name) {
            productCategories.forEach {
                if(it.id == list.last().parent) {
                    list += it
                    currentName = it.name
                }
            }
        }

        return list.asReversed()
    }

    fun getChildrenCategoryNames(name: String): List<String> {
        val productCategories = this.getEntities().asReversed()

        val root: ProductCategory
        try {
            root = productCategories.first { it.name == name }
        } catch (e: NoSuchElementException) {
            return emptyList()
        }

        val list = mutableListOf(Pair(root.id, root.name))

        var newAdded = true
        while(newAdded) {
            newAdded = false

            productCategories.forEach { category ->
                val idList = list.map { it.first }
                if(idList.contains(category.parent) && !idList.contains(category.id)) {
                    list += Pair(category.id, category.name)

                    newAdded = true
                }
            }
        }

        return list.map { it.second }
    }

}