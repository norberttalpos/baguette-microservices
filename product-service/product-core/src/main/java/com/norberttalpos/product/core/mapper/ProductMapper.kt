package com.norberttalpos.product.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import com.norberttalpos.common.abstracts.service.AbstractGettableService
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.core.entity.MeasurementUnit
import com.norberttalpos.product.core.entity.Product
import com.norberttalpos.product.core.entity.ProductBrand
import com.norberttalpos.product.core.entity.ProductCategory
import com.norberttalpos.product.core.repository.MeasurementUnitRepository
import com.norberttalpos.product.core.repository.ProductBrandRepository
import com.norberttalpos.product.core.repository.ProductCategoryRepository
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.springframework.beans.factory.annotation.Autowired

@Mapper(
    config = MapstructConfig::class,
    uses = [
        ProductBrandMapper::class,
        ProductCategoryMapper::class,
        MeasurementUnitMapper::class
    ]
)
abstract class ProductMapper : AbstractDtoMapper<Product, ProductDto>() {

    @Autowired
    private lateinit var productBrandService: AbstractGettableService<ProductBrand, ProductBrandRepository>

    @Autowired
    private lateinit var productCategoryService: AbstractGettableService<ProductCategory, ProductCategoryRepository>

    @Autowired
    private lateinit var measurementUnitService: AbstractGettableService<MeasurementUnit, MeasurementUnitRepository>

    @Mapping(target = "brand.products", ignore = true)
    @Mapping(target = "category.products", ignore = true)
    @Mapping(target = "price", ignore = true)
    abstract override fun toDto(entity: Product): ProductDto

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "measurementUnit", ignore = true)
    abstract override fun fromDto(dto: ProductDto): Product

    @AfterMapping
    fun fillReferences(dto: ProductDto, @MappingTarget entity: Product) {

        this.referenceResolver(dto.brand, entity::brand, this.productBrandService::getById)
        this.referenceResolver(dto.category, entity::category, this.productCategoryService::getById)
        this.referenceResolver(dto.measurementUnit, entity::measurementUnit, this.measurementUnitService::getById)
    }

    @AfterMapping
    fun fillReferences(entity: Product, @MappingTarget dto: ProductDto) {
        dto.price = entity.amount * entity.unitPrice
    }
}