package com.norberttalpos.product.core.mapper

import com.norberttalpos.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.product.api.dto.ProductDto
import com.norberttalpos.product.core.entity.Product
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", uses = [
    ProductBrandMapper::class,
    ProductCategoryMapper::class,
    MeasurementUnitMapper::class
])
abstract class ProductMapper : AbstractDtoMapper<Product, ProductDto> {

    @Mapping(target = "brand.products", ignore = true)
    @Mapping(target = "category.products", ignore = true)
    abstract override fun toDto(entity: Product): ProductDto
}