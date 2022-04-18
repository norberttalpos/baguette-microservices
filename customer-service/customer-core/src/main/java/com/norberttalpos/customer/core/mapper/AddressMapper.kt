package com.norberttalpos.customer.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import com.norberttalpos.customer.api.dto.AddressDto
import com.norberttalpos.customer.core.entity.Address
import org.mapstruct.Mapper

@Mapper(config = MapstructConfig::class)
abstract class AddressMapper : AbstractDtoMapper<Address, AddressDto>()