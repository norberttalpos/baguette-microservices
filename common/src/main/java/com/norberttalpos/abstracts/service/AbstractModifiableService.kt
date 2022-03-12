package com.norberttalpos.abstracts.service

import com.norberttalpos.abstracts.entity.AbstractEntity
import com.norberttalpos.abstracts.repository.AbstractRepository

abstract class AbstractModifiableService<ENTITY : AbstractEntity, FILTER, REPOSITORY : AbstractRepository<ENTITY>>(
    repository: REPOSITORY
) : AbstractGettableService<ENTITY, FILTER, REPOSITORY>(repository) {

    fun put(entity: ENTITY): ENTITY? {
        this.getById(entity.id!!)

        return if(this.validateEntity(entity)) {
            this.repository.save(entity)
            entity
        } else {
            null
        }
    }

    fun post(entity: ENTITY): ENTITY? {
        return if(this.validateEntity(entity)) {
            this.repository.save(entity)
            entity
        } else {
            null
        }
    }

    abstract fun validateEntity(entity: ENTITY): Boolean
}