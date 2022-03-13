package com.norberttalpos.product.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.product.api.dto.ProductCategoryDto
import com.norberttalpos.product.core.entity.ProductCategory
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = [ ProductMapper::class ])
abstract class ProductCategoryMapper : AbstractDtoMapper<ProductCategory, ProductCategoryDto>()