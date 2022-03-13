package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractModifiableService<ENTITY : AbstractEntity, FILTER : AbstractFilter, REPOSITORY : AbstractRepository<ENTITY>>
    : AbstractGettableService<ENTITY, FILTER, REPOSITORY>() {

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