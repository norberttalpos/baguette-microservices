package com.norberttalpos.product.core.repository

import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.product.core.entity.Product
import com.querydsl.core.types.Predicate
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : AbstractRepository<Product> {

    @EntityGraph(attributePaths = ["brand", "category", "measurementUnit"])
    override fun findAll(): MutableList<Product>

    @EntityGraph(attributePaths = ["brand", "category", "measurementUnit"])
    override fun findAll(predicate: Predicate): MutableIterable<Product>

    @EntityGraph(attributePaths = ["brand", "category", "measurementUnit"])
    override fun findById(id: Long): Optional<Product>
}