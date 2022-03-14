package com.norberttalpos.cart.core.mapper

import com.norberttalpos.cart.api.dto.CartItemDto
import com.norberttalpos.cart.core.entity.CartItem
import com.norberttalpos.common.abstracts.dto.AbstractDtoMapper
import com.norberttalpos.product.api.client.product.ProductClient
import com.norberttalpos.product.api.client.product.ProductGetByIdService
import org.mapstruct.*
import org.springframework.beans.factory.annotation.Autowired

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
abstract class CartItemMapper : AbstractDtoMapper<CartItem, CartItemDto>() {

    @Autowired
    private lateinit var productGetterService: ProductClient

    @Mapping(target = "product", ignore = true)
    @Mapping(target = "price", ignore = true)
    abstract override fun toDto(entity: CartItem): CartItemDto

    @AfterMapping
    fun fillReferences(entity: CartItem, @MappingTarget dto: CartItemDto) {
        entity.id?.let {
            dto.product = productGetterService.getProductById(entity.id!!)
            dto.price = (entity.amount * dto.product!!.price!!)
        }
    }
}