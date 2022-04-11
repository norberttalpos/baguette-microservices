package com.norberttalpos.customer.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.customer.api.dto.AddressDto
import com.norberttalpos.customer.core.entity.Address
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
abstract class AddressMapper : AbstractDtoMapper<Address, AddressDto>()