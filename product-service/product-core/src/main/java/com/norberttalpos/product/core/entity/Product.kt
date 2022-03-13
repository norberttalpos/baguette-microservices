package com.norberttalpos.product.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import javax.persistence.*

@Entity(name = "Product")
@Table(name = "product")
class Product : AbstractEntity() {

    @Column(name = "name", nullable = false, unique = true)
    var name: String = ""

    @Column(name = "unit_price")
    var unitPrice: Int = 0

    @Column(name = "amount")
    var amount: Int = 0

    @Column(name = "rating")
    var rating: Float = 0f

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_brand_id", nullable = false)
    lateinit var brand: ProductBrand

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_category_id", nullable = false)
    lateinit var category: ProductCategory

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "measurement_unit_id", nullable = false)
    lateinit var measurementUnit: MeasurementUnit
}