package com.norberttalpos.cart.core.repository

import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : AbstractRepository<Cart>