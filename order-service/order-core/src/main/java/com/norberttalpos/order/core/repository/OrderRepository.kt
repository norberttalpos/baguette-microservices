package com.norberttalpos.order.core.repository

import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.order.core.entity.Order
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : AbstractRepository<Order>