package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.filter.WhereMode
import com.norberttalpos.common.exception.NotValidUpdateException
import javax.persistence.EntityNotFoundException

abstract class AbstractModifiableService<ENTITY : AbstractEntity, FILTER : AbstractFilter>
    : AbstractCreatableService<ENTITY, FILTER>() {

    fun put(entity: ENTITY): ENTITY {
        entity.id ?: throw NotValidUpdateException("Provide an id for put request")
        this.getById(entity.id!!) ?: throw EntityNotFoundException()

        if (this.validateEntity(entity)) {
            this.repository.save(entity)

            return entity
        }
        else throw NotValidUpdateException("Not valid put")
    }
}