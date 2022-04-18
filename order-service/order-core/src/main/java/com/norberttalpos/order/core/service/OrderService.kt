package com.norberttalpos.order.core.service

import com.norberttalpos.common.abstracts.filter.QueryBuilder
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.service.AbstractDeletableService
import com.norberttalpos.common.abstracts.service.jwtRequiredMethod
import com.norberttalpos.customer.api.client.CustomerClient
import com.norberttalpos.order.api.filter.OrderFilter
import com.norberttalpos.order.core.entity.Order
import com.norberttalpos.order.core.entity.QOrder
import com.norberttalpos.order.core.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderService(
    private val customerClient: CustomerClient
) : AbstractDeletableService<Order, OrderFilter, OrderRepository>() {

    override fun filter(filter: OrderFilter, whereMode: WhereMode): List<Order> {
        val order: QOrder = QOrder.order
        val where = QueryBuilder(whereMode)

        filter.id?.let {
            where.add(order.id.eq(filter.id))
        }
        filter.cartId?.let {
            where.add(order.cartId.eq(filter.cartId))
        }
        filter.customerId?.let {
            where.add(order.customerId.eq(filter.customerId))
        }
        filter.status?.let {
            where.add(order.status.eq(filter.status))
        }

        return this.repository.findAll(where.builder).toList()
    }

    override fun provideUniquenessCheckFilter(entity: Order) =
        OrderFilter(cartId = entity.cartId, customerId = entity.customerId)

    override fun validateEntity(entity: Order): Boolean {
        try {
            var success = false
            jwtRequiredMethod { jwtToken: String ->
                 success = this.customerClient.userExistsById(entity.customerId!!, jwtToken).body!!
            }
            return success

        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }

    override fun checkUniqueness(entity: Order): Boolean {
        val collisions = this.filter(this.provideUniquenessCheckFilter(entity), WhereMode.AND)
        return collisions.isEmpty()
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun deleteCustomerOrders(customerId: Long) {
        val ordersOfCustomer = this.filter(OrderFilter(customerId = customerId))

        this.repository.deleteAll(ordersOfCustomer)
    }
}