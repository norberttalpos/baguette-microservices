package com.norberttalpos.product.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity(name = "ProductBrand")
@Table(name = "product_brand")
class ProductBrand : AbstractEntity() {
    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "country", nullable = false)
    var country: String = ""

    @OneToMany(mappedBy = "brand")
    var products: List<Product> = emptyList()
}