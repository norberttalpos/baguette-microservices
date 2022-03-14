package com.norberttalpos.cart.core.mapper

import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import org.mapstruct.*

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
    uses = [CartItemMapper::class])
abstract class CartMapper : AbstractDtoMapper<Cart, CartDto>() {

    @AfterMapping
    fun fillReferences(entity: Cart, @MappingTarget dto: CartDto) {
        dto.totalPrice = dto.cartItems?.map { it.price ?: 0.0 }?.toList()?.sum() ?: 0.0
    }
}