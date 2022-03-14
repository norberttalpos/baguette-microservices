package com.norberttalpos.cart.api.controller

import com.norberttalpos.cart.api.dto.CartDto
import com.norberttalpos.cart.api.filter.CartFilter
import com.norberttalpos.common.abstracts.controller.interfaces.AbstractDeletableController

interface CartController : AbstractDeletableController<CartDto, CartFilter>