package com.norberttalpos.customer.core.repository

import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.customer.core.entity.Customer
import com.querydsl.core.types.Predicate
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : AbstractRepository<Customer> {

    @EntityGraph(attributePaths = ["address"])
    fun getByEmail(email: String): Customer?

    @EntityGraph(attributePaths = ["address"])
    override fun findAll(): MutableList<Customer>

    @EntityGraph(attributePaths = ["address"])
    override fun findAll(predicate: Predicate): MutableIterable<Customer>

    @EntityGraph(attributePaths = ["address"])
    override fun findById(id: Long): Optional<Customer>
}