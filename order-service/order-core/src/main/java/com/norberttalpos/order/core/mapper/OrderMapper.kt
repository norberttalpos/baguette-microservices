package com.norberttalpos.order.core.mapper

import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import com.norberttalpos.order.api.dto.OrderDto
import com.norberttalpos.order.core.entity.Order
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(config = MapstructConfig::class)
abstract class OrderMapper : AbstractDtoMapper<Order, OrderDto>()