package com.norberttalpos.abstracts.service

import com.norberttalpos.abstracts.entity.AbstractEntity
import com.norberttalpos.abstracts.repository.AbstractRepository

abstract class AbstractDeletableService<ENTITY : AbstractEntity, FILTER, REPOSITORY : AbstractRepository<ENTITY>>(
    repository: REPOSITORY
) : AbstractModifiableService<ENTITY, FILTER, REPOSITORY>(repository) {

    fun deleteById(id: Long) = this.repository.deleteById(id)
}