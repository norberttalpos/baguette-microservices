package com.norberttalpos.order.api.controller

import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController
import com.norberttalpos.order.api.dto.OrderDto
import com.norberttalpos.order.api.filter.OrderFilter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
@Tag(name = "Order")
interface OrderController : AbstractDeletableController<OrderDto, OrderFilter>