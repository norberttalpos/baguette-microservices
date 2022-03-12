package com.norberttalpos.product.core.entity

import com.norberttalpos.abstracts.entity.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity(name = "ProductCategory")
@Table(name = "product_category")
class ProductCategory : AbstractEntity() {

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "parent")
    var parent: Int = 0

    @OneToMany(mappedBy = "category")
    var products: List<Product> = emptyList()
}