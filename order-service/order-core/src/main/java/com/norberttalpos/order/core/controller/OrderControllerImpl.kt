package com.norberttalpos.order.core.controller

import com.norberttalpos.auth.api.dto.UserDto
import com.norberttalpos.common.abstracts.controller.implementations.AbstractDeletableControllerImpl
import com.norberttalpos.order.api.controller.OrderController
import com.norberttalpos.order.api.dto.OrderDto
import com.norberttalpos.order.api.filter.OrderFilter
import com.norberttalpos.order.core.entity.Order
import com.norberttalpos.order.core.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderControllerImpl : OrderController,
    AbstractDeletableControllerImpl<OrderDto, Order, OrderFilter, OrderService>() {

    override fun getOrdersOfCustomer(currentUser: UserDto): ResponseEntity<List<OrderDto>> {
        return this.filter(OrderFilter(customerId = currentUser.id))
    }

    override fun deleteCustomerOrders(currentUser: UserDto)
        = this.commandMethod {
            this.service.deleteCustomerOrders(currentUser.id!!)
        }
}