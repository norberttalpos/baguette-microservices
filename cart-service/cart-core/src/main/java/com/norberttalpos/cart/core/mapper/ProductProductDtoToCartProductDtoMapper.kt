package com.norberttalpos.cart.core.mapper

import com.norberttalpos.cart.api.dto.ProductDto
import com.norberttalpos.common.abstracts.dto.AbstractDtoToDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import org.mapstruct.*

@Mapper(config = MapstructConfig::class)
abstract class ProductProductDtoToCartProductDtoMapper
    : AbstractDtoToDtoMapper<com.norberttalpos.product.api.dto.ProductDto, ProductDto>() {

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "measurementUnit", ignore = true)
    @Mapping(target = "category", ignore = true)
    abstract override fun toDto2(dtO1: com.norberttalpos.product.api.dto.ProductDto): ProductDto

    @AfterMapping
    fun fillReferences(dto1: com.norberttalpos.product.api.dto.ProductDto, @MappingTarget dto2: ProductDto) {
        dto2.brand = dto1.brand?.name
        dto2.measurementUnit = dto1.measurementUnit?.name
        dto2.category = dto1.category?.name
    }
}