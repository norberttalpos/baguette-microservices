package com.norberttalpos.product.core

import com.norberttalpos.product.core.entity.MeasurementUnit
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.core.entity.ProductBrand
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.service.MeasurementUnitService
import com.norberttalpos.product.core.service.ProductBrandService
import com.norberttalpos.product.core.service.ProductCategoryService
import com.norberttalpos.product.core.service.ProductService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EntityScan(basePackages = [ "com.norberttalpos.product.core.entity" ])
open class ProductServiceApplication {
    @Bean
    open fun run(
        productService: ProductService,
        productBrandService: ProductBrandService,
        productCategoryService: ProductCategoryService,
        measurementUnitService: MeasurementUnitService,
    ) = CommandLineRunner {
        val measurementUnit = MeasurementUnit().apply {
            this.name = "g"
        }
        measurementUnitService.post(measurementUnit)

        val productBrand = ProductBrand().apply {
            this.name = "Pick"
            this.country = "Hungary"
        }
        productBrandService.post(productBrand)

        val productCategory = ProductCategory().apply {
            this.name = "Meat"
        }
        productCategoryService.post(productCategory)

        val product = Product().apply {
            this.name = "Téliszalámi"
            this.amount = 500
            this.rating = 9.1f
            this.brand = productBrand
            this.category = productCategory
            this.measurementUnit = measurementUnit
        }
        productService.post(product)
    }
}

fun main(args: Array<String>) {
    runApplication<ProductServiceApplication>(*args)
}