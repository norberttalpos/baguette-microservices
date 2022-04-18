package com.norberttalpos.cart.core.mapper

import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.common.abstracts.dto.MapstructConfig
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(
    config = MapstructConfig::class,
    uses = [CartItemMapper::class]
)
abstract class CartMapper : AbstractDtoMapper<Cart, CartDto>() {

    @AfterMapping
    fun fillReferences(@MappingTarget dto: CartDto) {
        dto.totalPrice = dto.cartItems?.map { it.price ?: 0.0 }?.toList()?.sum() ?: 0.0
    }
}