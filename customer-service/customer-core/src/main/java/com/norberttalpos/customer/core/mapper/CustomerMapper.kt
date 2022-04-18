package com.norberttalpos.customer.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import com.norberttalpos.customer.api.dto.CustomerDto
import com.norberttalpos.customer.core.entity.Customer
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(
    config = MapstructConfig::class,
    uses = [AddressMapper::class],
)
abstract class CustomerMapper : AbstractDtoMapper<Customer, CustomerDto>() {


    @Mapping(target = "address", ignore = true)
    abstract override fun fromDto(dto: CustomerDto): Customer
}