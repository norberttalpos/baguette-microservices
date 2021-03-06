package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.common.exception.NotValidUpdateException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

abstract class AbstractCreatableService<
        ENTITY : AbstractEntity,
        FILTER : AbstractFilter,
        REPOSITORY : AbstractRepository<ENTITY>
        >
    : AbstractFilterableService<ENTITY, FILTER, REPOSITORY>() {

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = [Exception::class])
    fun post(entity: ENTITY): ENTITY {
        if(this.validatePost(entity)) {

            this.preCreation(entity)
            this.repository.saveAndFlush(entity)
            this.postCreation(entity)

            return entity
        }
        else throw NotValidUpdateException("Not valid post")
    }

    fun preCreation(entity: ENTITY) {}

    fun postCreation(entity: ENTITY) {}

    private fun validatePost(entity: ENTITY) = this.checkUniqueness(entity) && this.validateEntity(entity)

    protected fun checkUniqueness(entity: ENTITY): Boolean {
        val collisions = this.filter(this.provideUniquenessCheckFilter(entity), WhereMode.OR)
        return collisions.isEmpty()
    }

    abstract fun provideUniquenessCheckFilter(entity: ENTITY): FILTER

    abstract fun validateEntity(entity: ENTITY): Boolean
}

fun jwtRequiredMethod(command: (jwt: String) -> Unit) {
    val jwtToken: String?

    try {
        jwtToken = SecurityContextHolder.getContext().authentication.credentials as? String
    } catch (e: Exception) {
        throw NotValidUpdateException("Operation requires a user token")
    }

    jwtToken?.let {
        command.invoke(jwtToken)
    }
}
