package com.norberttalpos.product.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import com.norberttalpos.product.api.dto.ProductCategoryDto
import com.norberttalpos.product.core.entity.ProductCategory
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    config = MapstructConfig::class,
    uses = [ProductMapper::class],
)
abstract class ProductCategoryMapper : AbstractDtoMapper<ProductCategory, ProductCategoryDto>() {

    @Mapping(target = "products", ignore = true)
    abstract fun toDtoWithoutProducts(entity: ProductCategory): ProductCategoryDto
}