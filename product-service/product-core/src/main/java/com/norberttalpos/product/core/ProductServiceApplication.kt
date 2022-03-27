package com.norberttalpos.product.core

import com.norberttalpos.common.configs.ResourceServerWebSecurityConfig
import com.norberttalpos.product.core.entity.MeasurementUnit
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.core.entity.ProductBrand
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.service.MeasurementUnitService
import com.norberttalpos.product.core.service.ProductBrandService
import com.norberttalpos.product.core.service.ProductCategoryService
import com.norberttalpos.product.core.service.ProductService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

@SpringBootApplication
@EnableEurekaClient
@EntityScan(
    basePackages = [ "com.norberttalpos.product.core.entity" ]
)
@Import(
    ResourceServerWebSecurityConfig::class
)
@OpenAPIDefinition(
    info = Info(title = "Product API", version = "1.0", description = "Documentation Product API v1.0")
)
class ProductServiceApplication {
    @Bean
    fun run(
        productService: ProductService,
        productBrandService: ProductBrandService,
        productCategoryService: ProductCategoryService,
        measurementUnitService: MeasurementUnitService,
    ) = CommandLineRunner {
        val measurementUnit = MeasurementUnit().apply {
            this.name = "kg"
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
            this.amount = 0.5
            this.available = 100
            this.rating = 9.1
            this.unitPrice = 5000
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