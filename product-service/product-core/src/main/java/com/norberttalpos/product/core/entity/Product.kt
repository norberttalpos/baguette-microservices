package com.norberttalpos.product.core.entity

import com.norberttalpos.common.abstracts.entity.AbstractAutoGeneratedIdEntity
import javax.persistence.*

@Entity(name = "Product")
@Table(name = "product")
class Product : AbstractAutoGeneratedIdEntity() {

    @Column(name = "name", nullable = false, unique = true)
    var name: String = ""

    @Column(name = "unit_price", nullable = false)
    var unitPrice: Int = 0

    @Column(name = "amount", nullable = false)
    var amount: Double = 0.0

    @Column(name = "quantity", nullable = false)
    var quantity: Int = 0

    @Column(name = "rating")
    var rating: Double = 0.0

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