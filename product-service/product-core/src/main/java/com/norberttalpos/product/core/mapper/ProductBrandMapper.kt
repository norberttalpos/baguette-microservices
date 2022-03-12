package com.norberttalpos.product.core.mapper

import com.norberttalpos.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.product.api.dto.ProductBrandDto
import com.norberttalpos.product.core.entity.ProductBrand
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [ ProductMapper::class ])
abstract class ProductBrandMapper : AbstractDtoMapper<ProductBrand, ProductBrandDto>