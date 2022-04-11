package com.norberttalpos.cart.api.controller.payload

import java.util.*

data class CreateCartRequest(
    var userId: UUID? = null
)