package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.exception.NotValidUpdateException
import javax.persistence.EntityNotFoundException

abstract class AbstractCreatableService<ENTITY : AbstractEntity, FILTER : AbstractFilter>
    : AbstractFilterableService<ENTITY, FILTER>() {

    fun post(entity: ENTITY): ENTITY {
        if(this.validatePost(entity)) {
            this.repository.save(entity)

            return entity
        }
        else throw NotValidUpdateException("Not valid post")
    }

    private fun validatePost(entity: ENTITY) = this.checkUniqueness(entity) && this.validateEntity(entity)

    private fun checkUniqueness(entity: ENTITY): Boolean {
        val collisions = this.filter(this.provideUniquenessCheckFilter(entity), WhereMode.OR)
        return collisions.isEmpty()
    }

    abstract fun provideUniquenessCheckFilter(entity: ENTITY): FILTER

    abstract fun validateEntity(entity: ENTITY): Boolean
}