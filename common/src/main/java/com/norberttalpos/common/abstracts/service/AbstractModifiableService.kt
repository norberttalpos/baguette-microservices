package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.exception.NotValidUpdateException
import javax.persistence.EntityNotFoundException

abstract class AbstractModifiableService<ENTITY : AbstractEntity, FILTER : AbstractFilter>
    : AbstractFilterableService<ENTITY, FILTER>() {

    fun put(entity: ENTITY): ENTITY {
        entity.id ?: throw NotValidUpdateException("Provide an id for put request")
        this.getById(entity.id!!) ?: throw EntityNotFoundException()

        return if (this.validateEntity(entity)) {
            this.repository.save(entity)
            entity
        }
        else throw NotValidUpdateException("Not valid put")
    }

    fun post(entity: ENTITY): ENTITY {
        return if(this.validatePost(entity)) {
            this.repository.save(entity)
            entity
        } else {
            throw NotValidUpdateException("Not valid post")
        }
    }

    private fun validatePost(entity: ENTITY) = this.checkUniqueness(entity) && this.validateEntity(entity)

    private fun checkUniqueness(entity: ENTITY): Boolean {
        val collisions = this.filter(this.provideUniquenessCheckFilter(entity), WhereMode.OR)
        return collisions.isEmpty()
    }

    abstract fun provideUniquenessCheckFilter(entity: ENTITY): FILTER

    abstract fun validateEntity(entity: ENTITY): Boolean
}