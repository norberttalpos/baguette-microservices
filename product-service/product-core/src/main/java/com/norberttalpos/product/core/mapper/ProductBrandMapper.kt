package com.norberttalpos.product.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.product.api.dto.ProductBrandDto
import com.norberttalpos.product.core.entity.ProductBrand
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
    uses = [ ProductMapper::class ])
abstract class ProductBrandMapper : AbstractDtoMapper<ProductBrand, ProductBrandDto>()