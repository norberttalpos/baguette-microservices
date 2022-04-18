package com.norberttalpos.cart.core.repository

import com.norberttalpos.cart.core.entity.Cart
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.querydsl.core.types.Predicate
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository : AbstractRepository<Cart> {

    @EntityGraph(attributePaths = ["cartItems"])
    override fun findAll(): MutableList<Cart>

    @EntityGraph(attributePaths = ["cartItems"])
    override fun findAll(predicate: Predicate): MutableIterable<Cart>

    @EntityGraph(attributePaths = ["cartItems"])
    override fun findById(id: UUID): Optional<Cart>
}