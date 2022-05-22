package com.norberttalpos.common.abstracts.service

import com.norberttalpos.common.abstracts.entity.AbstractEntity
import com.norberttalpos.common.abstracts.filter.AbstractFilter
import com.norberttalpos.common.abstracts.repository.AbstractRepository
import com.norberttalpos.common.exception.NotValidUpdateException
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

abstract class AbstractModifiableService<
        ENTITY : AbstractEntity,
        FILTER : AbstractFilter,
        REPOSITORY : AbstractRepository<ENTITY>
        >
    : AbstractCreatableService<ENTITY, FILTER, REPOSITORY>() {

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = [Exception::class])
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